importPackage(java.util);

var first  = ["Meg", "Ex", "Ant", "Mas", "Ox", "Ax", "Bis"];
var second = ["or", "an", "net", "et", "en"];
var third  = ["ite", "ium"];

exoticNames = function() {

//function getNames() {
    var list = new ArrayList();
    for(var s1 in first) {
        for(var s2 in second) {
            for(var s3 in third) {
                list.add(s1 + s2 + s3);
            }
        }
    }
    return list;
}