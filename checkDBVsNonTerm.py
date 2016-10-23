from os.path import exists

grammarfile='/home/rubbernecker/ifuzzer_s/langparser/JavaScript.g4'
fragpoolfolder="/home/rubbernecker/ifuzzer_s/database/"

non_Terminals=[]

def set_bnf(bnf):
        def strip_spaces(key, values):
            values = [value.strip()
                for value in values.split('|') if value]
            return values

        bnf_dict = {}
        for item in bnf.split('\n'):
                if item.find(':') >= 0:
                    key, values = item.split(':',1)
                    key = key.strip()
                    bnf_dict[key] = strip_spaces(key, values)
                    non_Terminals.append(key)
                elif item:
                    values = bnf_dict[key]
                    values.extend(strip_spaces(key, item))
                    bnf_dict[key] = values
                else:
                    pass

def _extractProductions():
        bnf=""
        f = open(grammarfile,'r')
        for line in f:
            bnf+=line;
        f.close()        
        set_bnf(bnf)

def _verifyDBFiles():
    _extractProductions()
    for nonTerm in non_Terminals:
        if not exists(fragpoolfolder+nonTerm):
            print nonTerm

if __name__ == '__main__':
    _verifyDBFiles()


