importPackage(java.util);

var first  = ["Snake", "Blood", "Gleam", "String", "Witch's "];
var second = ["thorn", "root", "leaf", "stem", "blade", "grass",
            "bell", "flower", "bloom", "blossom"];

var exoticNames = getNames();

function getNames() {
    var list = new ArrayList();
    for(var s1 in first) {
        for(var s2 in second) {
            list.add(first[s1] + second[s2]);
        }
    }
    return list;
}