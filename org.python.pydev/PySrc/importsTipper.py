#ok, let,s fix some things that may not work on jython depending on the version
try:
    __setFalse = False
except NameError:
    False = 0
    True = 1


class InfoHolder:
    INSPECT_AVAILABLE = True

try:
    import inspect
except:
    InfoHolder.INSPECT_AVAILABLE = False

import sys


#completion types.
TYPE_UNKNOWN = -1
TYPE_IMPORT = 0
TYPE_CLASS = 1
TYPE_FUNCTION = 2
TYPE_ATTR = 3
TYPE_BUILTIN = 4
TYPE_PARAM = 5

TYPE_BUILTIN_STR = '4'

def _imp(name):
    try:
        return __import__(name)
    except:
        if '.' in name:
            sub = name[0:name.rfind('.')]
            return _imp(sub)
        else:
            s = 'Unable to import module: %s - sys.path: %s' % (str(name), sys.path)
            raise RuntimeError(s)

def Find( name ):
    mod = _imp(name)
    components = name.split('.')

    for comp in components[1:]:
        mod = getattr(mod, comp)
    return mod


def GenerateTip( data ):
    data = data.replace( '\n', '' )
    if data.endswith( '.' ):
        data = data.rstrip( '.' )
    
    mod = Find( data )
    tips = GenerateImportsTipForModule( mod )
    return tips
    
    


def GenerateImportsTipForModule( mod ):
    '''
    @param mod: the module from where we should get the completions
    '''
    ret = []
    
    dirComps = dir( mod )
    getCompleteInfo = True
    
    if len(dirComps) > 1000:
        #ok, we don't want to let our users wait forever... 
        #no complete info for you...
        
        getCompleteInfo = False
    
    if not InfoHolder.INSPECT_AVAILABLE:
        #there's no way to get complete info if the inspect is not available
        getCompleteInfo = False
    
    dontGetDocsOn = (float, int, str, tuple, list, type)
    for d in dirComps:

        args = ''

        if getCompleteInfo:
            obj = getattr(mod, d)
            retType = TYPE_BUILTIN

            if inspect.ismethod(obj) or inspect.isbuiltin(obj) or inspect.isfunction(obj) or inspect.isroutine(obj):
                try:
                    args, vargs, kwargs, defaults = inspect.getargspec( obj )
                        
                    r = ''
                    for a in ( args ):
                        if len( r ) > 0:
                            r += ', '
                        r += str( a )
                    args = '(%s)' % (r)
                except TypeError:
                    args = '()'

                retType = TYPE_FUNCTION
            
            
            #check if we have to get docs
            getDoc = True
            for class_ in dontGetDocsOn:
                if isinstance(obj, class_):
                    getDoc = False
                    break
                    
            doc = ''
            if getDoc:
                #no need to get this info... too many constants are defined and 
                #makes things much slower (passing all that through sockets takes quite some time)
                doc = inspect.getdoc( obj )
            
            #add token and doc to return - assure only strings.
            ret.append(   (d, doc, args, str(retType))   )
            
        else: #getCompleteInfo == False
        
            #ok, no complete info, let's try to do this as fast and clean as possible
            #so, no docs for this kind of information, only the signatures
            ret.append(   (d, '', args, TYPE_BUILTIN_STR)   )
            
    return ret


    
    