importPackage(java.util);

var first  = ["Ac", "Za", "Mat", "Ni"];
var second = ["tan", "rop", "cat", "mat"];
var third  = ["ta", "in", "or", "za"];

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