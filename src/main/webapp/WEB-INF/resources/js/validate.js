function inputValidation() {
    var doc = document;
    doc.getElementById("username").onkeypress = function (event) {
        event = event || window.event;
        return (event.charCode > 47 && event.charCode < 58) ||
            (event.charCode > 96 && event.charCode < 123);
    };
    doc.getElementById("password").onkeypress = function (event) {
        event = event || window.event;
        return (event.charCode > 47 && event.charCode < 58) ||
            (event.charCode > 64 && event.charCode < 91) ||
            (event.charCode > 96 && event.charCode < 123);
    };
}