importPackage(java.util);

var first  = ["Blood", "Dragon", "Shadow", "Sky", "Thunder"];
var second = ["stone", "rock", "gem", "spar", "quartz", "glass", "jewel"];

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