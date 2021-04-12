//String variable passwordRepeatError is required to work correctly!
function validateForm() {
    var pw = document.getElementById("pw").value;
    var repPw = document.getElementById("repPw").value;
    if (pw != repPw) {
        alert(passwordRepeatError);
        return false;
    }
}