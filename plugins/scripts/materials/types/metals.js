importPackage(java.util);

var first  = ["Meg", "Ex", "Ant", "Mas", "Ox", "Ax", "Bis"];
var second = ["or", "an", "net", "et", "en"];
var third  = ["ite", "ium"];

var exoticNames = getNames();

function getNames() {
    var list = new ArrayList();
    for(var s1 in first) {
        for(var s2 in second) {
            for(var s3 in third) {
                list.add(first[s1] + second[s2] + third[s3]);
            }
        }
    }
    return list;
}