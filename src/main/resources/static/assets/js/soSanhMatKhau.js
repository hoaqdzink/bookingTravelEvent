function checkPass(fieldConfirmPassword){
    if(fieldConfirmPassword.value != $("#password").val()){
        fieldConfirmPassword.setCustomValidity("Mật khẩu không giống");
    }else{
        fieldConfirmPassword.setCustomValidity("");
    }
}