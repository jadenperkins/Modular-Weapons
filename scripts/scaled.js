var MULT_CAP = 2000;
var CAP_LEVEL = 100;
var HALF_LEVEL = CAP_LEVEL / 2;

var BASE = Math.pow(MULT_CAP / 2, 1 / (HALF_LEVEL - 1));

function scale(i, original) {
    if(i <= HALF_LEVEL) return original * Math.pow(BASE, i - 1);
    if(i > HALF_LEVEL) return original * (MULT_CAP - Math.pow(BASE, CAP_LEVEL - 1 - i));
}