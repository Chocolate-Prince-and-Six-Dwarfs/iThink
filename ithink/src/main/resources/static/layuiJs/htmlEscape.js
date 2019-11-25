function htmlEscape(text){
    if(text==null||text==""){
        return "";
    }else{
        return text.replace(/[<>"&]/g, function(match, pos, originalText){
            switch(match){
                case "<": return "&lt;";
                case ">":return "&gt;";
                case "&":return "&amp;";
                case "\"":return "&quot;";
            }
        });
    }
}