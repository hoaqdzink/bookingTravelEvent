function validateRegisterStaff() {
    var status = false;
    var index = 0;

    var username = document.getElementById("username");
    var fullname = document.getElementById("fullname");
    var birthDay = document.getElementById("birthDay");
    var email = document.getElementById("email").value;
    var address = document.getElementById("address");
    var telephone = document.getElementById("telephone");
    var password = document.getElementById("password");
    var imageFile = document.getElementById("imageFile");
    var image = document.getElementById("imageHINH");
    var gender = document.querySelector('input[id="gender"]:checked');
    var roles = document.querySelector('input[id="roles"]:checked');

    var errorUsername = document.getElementById("errorUsername");
    var errorFullname = document.getElementById("errorFullname");
    var errorBirthDay = document.getElementById("errorBirthDay");
    var errorEmail = document.getElementById("errorEmail");
    var errorAddress = document.getElementById("errorAddress");
    var errorTelephone = document.getElementById("errorTelephone");
    var errorPassword = document.getElementById("errorPassword");
    var errorImage = document.getElementById("errorImage");
    var errorGender = document.getElementById("errorGender");
    var errorRoles = document.getElementById("errorRoles");

    var atpos = email.indexOf("@");
    var dotpos = email.lastIndexOf(".");
    // var time = new Date.now();
    // console.log(time);
    ////KIỂM TRA TÊN ĐĂNG NHẬP
    if (username.value == "") {
        text = `<i class="fas fa-exclamation-circle"></i>Vui lòng điền vào tên đăng nhập`;
        errorUsername.innerHTML = text;
    } else {
        errorUsername.innerHTML = '';
        index++;
    }

    ////KIỂM TRA HỌ TÊN
    if (fullname.value == "") {
        text = `<i class="fas fa-exclamation-circle"></i>Vui lòng điền vào họ tên`;
        errorFullname.innerHTML = text;
    } else {
        errorFullname.innerHTML = '';
        index++;
    }

    ////KIỂM TRA NGÀY SINH
    if (birthDay.value == "") {
        text = `<i class="fas fa-exclamation-circle"></i>Vui lòng ngày sinh`;
        errorBirthDay.innerHTML = text;
    } else {
        errorBirthDay.innerHTML = '';
        index++;
    }

    ///KIỂM TRA EMAIL
    if (email == "") {
        text = `<i class="fas fa-exclamation-circle"></i>Vui lòng nhập vào email`;
        errorEmail.innerHTML = text;

    } else if (atpos < 1 || (dotpos - atpos < 2)) {
        text = `<i class="fas fa-exclamation-circle"></i>Chưa đúng định dạng email`;
        errorEmail.innerHTML = text;
    } else {
        errorEmail.innerHTML = '';
        index++;
    }

    ///KIỂM TRA ĐỊA CHỈ
    if (address.value == "") {
        text = `<i class="fas fa-exclamation-circle"></i>Vui lòng nhập vào địa chỉ`;
        errorAddress.innerHTML = text;
    } else {
        errorAddress.innerHTML = '';
        index++;
    }

    ///KIỂM TRA SỐ ĐIỆN THOẠI
    if (telephone.value == "") {
        text = `<i class="fas fa-exclamation-circle"></i>Vui lòng nhập vào số điện thoại`;
        errorTelephone.innerHTML = text;
    } else if (telephone.value.length < 10) {
        text = `<i class="fas fa-exclamation-circle"></i>Số điện thoại phải bằng 10 số`;
        errorTelephone.innerHTML = text;
    } else {
        errorTelephone.innerHTML = '';
        index++;
    }

    ///KIỂM TRA MẬT KHẨU
    if (password.value == "") {
        text = `<i class="fas fa-exclamation-circle"></i>Vui lòng nhập vào mật khẩu`;
        errorPassword.innerHTML = text;
    } else {
        errorPassword.innerHTML = '';
        index++;
    }

    ///KIỂM TRA HÌNH ẢNH
    if (imageFile.value == "" && image.value == "") {
        text = `<i class="fas fa-exclamation-circle"></i>Vui lòng chọn ảnh đại diện`;
        errorImage.innerHTML = text;

    } else {
        errorImage.innerHTML = '';
        index++;
    }

    ////KIỂM TRA QUYỀN
    if (!roles) {
        text = `<i class="fas fa-exclamation-circle"></i>Vui lòng chọn quyền`;
        errorRoles.innerHTML = text;
    } else {
        errorRoles.innerHTML = '';
        index++;
    }

    ///KIỂM TRA GIỚI TÍNH
    if (!gender) {
        text = `<i class="fas fa-exclamation-circle"></i>Vui lòng chọn giới tính`;
        errorGender.innerHTML = text;
    } else {
        errorGender.innerHTML = '';
        index++;
    }

    if (index == 10) {
        status = true;
    } else {
        status = false;
    }
    return status;
}

function validateFormTourPost() {
    var status = false;
    var index = 0;
    ///LẤY BIẾN INPUT
    var name = document.getElementById("name");
    var priceTour = document.getElementById("priceTour");
    var priceAdult = document.getElementById("priceAdult");
    var priceBaby = document.getElementById("priceBaby");
    var priceChildren = document.getElementById("priceChildren");
    var toCity = document.getElementById("toCity");
    var fromCity = document.getElementById("fromCity");
    var region = document.getElementById("region");
    var type = document.getElementById("typeTour");
    var tranport = document.getElementById("tranport");
    var time = document.getElementById("time");
    var mainImage = document.getElementById("mainImage");
    var hinhCHINH = document.getElementById("hinhCHINH");
    var moTaTour = document.getElementById("moTaTour");
    var extraImage = document.querySelectorAll(".extraImage");
    var hinhPHU = document.querySelectorAll(".hinhPHU");

    ////LẤY BIẾN MESSAGE THÔNG BÁO
    var errorName = document.getElementById("errorName");
    var errorPriceTour = document.getElementById("errorPriceTour");
    var errorPriceAdult = document.getElementById("errorPriceAdult");
    var errorPriceBaby = document.getElementById("errorPriceBaby");
    var errorPriceChildren = document.getElementById("errorPriceChildren");
    var errorFromCity = document.getElementById("errorFromCity");
    var errorToCity = document.getElementById("errorToCity");
    var errorRegion = document.getElementById("errorRegion");
    var errorType = document.getElementById("errorType");
    var errorTranport = document.getElementById("errorTranport");
    var errorTime = document.getElementById("errorTime");
    var errorPrimaryImage = document.getElementById("errorPrimaryImage");
    var errorDescriptionTour = document.getElementById("errorDescriptionTour");

    ///--KIỂM TRA TÊN TOUR
    if (name.value == '') {
        text = `<i class="fas fa-exclamation-circle"></i>Vui lòng điền vào tên`;
        errorName.innerHTML = text;

    } else {
        errorName.innerHTML = '';

        index++;
    }
    ///---KIỂM TRA GIÁ TOUR
    if (priceTour.value == '') {
        text = `<i class="fas fa-exclamation-circle"></i>Vui lòng điền vào giá tour`;
        errorPriceTour.innerHTML = text;

    } else if (isNaN(priceTour.value)) {
        text = `<i class="fas fa-exclamation-circle"></i>Mệnh giá tiền phải là số`;
        errorPriceTour.innerHTML = text;

    } else {
        errorPriceTour.innerHTML = '';

        index++;
    }

    ///---KIỂM TRA GIÁ NGƯỜI LỚN
    if (priceAdult.value == '') {
        text = `<i class="fas fa-exclamation-circle"></i>Vui lòng điền vào giá người lớn`;
        errorPriceAdult.innerHTML = text;

    } else if (isNaN(priceAdult.value)) {
        text = `<i class="fas fa-exclamation-circle"></i>Mệnh giá tiền phải là số`;
        errorPriceAdult.innerHTML = text;

    } else {
        errorPriceAdult.innerHTML = '';

        index++;
    }

    ///---KIỂM TRA GIÁ TRẺ EM
    if (priceChildren.value == '') {
        text = `<i class="fas fa-exclamation-circle"></i>Vui lòng điền vào giá trẻ em`;
        errorPriceChildren.innerHTML = text;

    } else if (isNaN(priceChildren.value)) {
        text = `<i class="fas fa-exclamation-circle"></i>Mệnh giá tiền phải là số`;
        errorPriceChildren.innerHTML = text;

    } else {
        errorPriceChildren.innerHTML = '';

        index++;
    }

    ///---KIỂM TRA GIÁ EM BÉ
    if (priceBaby.value == '') {
        text = `<i class="fas fa-exclamation-circle"></i>Vui lòng điền vào giá em bé`;
        errorPriceBaby.innerHTML = text;

    } else if (isNaN(priceBaby.value)) {
        text = `<i class="fas fa-exclamation-circle"></i>Mệnh giá tiền phải là số`;
        errorPriceBaby.innerHTML = text;

    } else {
        errorPriceBaby.innerHTML = '';

        index++;
    }

    ///KIỂM TRA VÙNG
    if (region.value == '0') {
        text = `<i class="fas fa-exclamation-circle"></i>Vui lòng chọn vùng`;
        errorRegion.innerHTML = text;

    } else {
        errorRegion.innerHTML = '';

        index++;
    }

    ///KIỂM TRA ĐIỂM KHỜI HÀNH
    if (fromCity.value == '0') {
        text = `<i class="fas fa-exclamation-circle"></i>Vui lòng chọn khởi hành`;
        errorFromCity.innerHTML = text;

    } else {
        errorFromCity.innerHTML = '';

        index++;
    }

    ////KIỂM TRA ĐIỂM ĐẾN
    if (toCity.value == "") {
        text = `<i class="fas fa-exclamation-circle"></i>Vui lòng chọn điểm đến`;
        errorToCity.innerHTML = text;

    } else {
        errorToCity.innerHTML = '';

        index++;
    }

    ////KIỂM TRA LOẠI
    if (type.value == "0") {
        text = `<i class="fas fa-exclamation-circle"></i>Vui lòng chọn loại`;
        errorType.innerHTML = text;

    } else {
        errorType.innerHTML = '';

        index++;
    }

    ///KIỂM TRA PHƯƠNG TIỆN
    if (tranport.value == "0") {
        text = `<i class="fas fa-exclamation-circle"></i>Vui lòng chọn phương tiện`;
        errorTranport.innerHTML = text;

    } else {
        errorTranport.innerHTML = '';

        index++;
    }

    ///KIỂM TRA THỜI GIAN TOUR
    if (time.value == "") {
        text = `<i class="fas fa-exclamation-circle"></i>Vui lòng điền vào thời gian chuyến đi`;
        errorTime.innerHTML = text;

    } else {
        errorTime.innerHTML = '';

        index++;
    }

    ///KIỂM TRA MÔ TẢ TOUR
    // if(moTaTour.value == ""){
    //     text = `<i class="fas fa-exclamation-circle"></i>Vui lòng điền vào mô tả cho tour`;
    //     errorDescriptionTour.innerHTML = text;
    //     
    // }else{
    //     errorDescriptionTour.innerHTML = '';
    //     
    //     index++;
    // }

    ///KIỂM TRA HÌNH CHÍNH 
    if (mainImage.value == "" && hinhCHINH.value == "") {
        text = `<i class="fas fa-exclamation-circle"></i>Vui lòng chọn đầy đủ các hình`;
        errorPrimaryImage.innerHTML = text;

    } else {
        errorPrimaryImage.innerHTML = '';

        index++;
    }

    for (var i = 0; i < extraImage.length; i++) {
        if (extraImage[i].value == "" && hinhPHU[i].value == "") {
            text = `<i class="fas fa-exclamation-circle"></i>Vui lòng chọn đầy đủ các hình`;
            errorPrimaryImage.innerHTML = text;
        } else {
            errorPrimaryImage.innerHTML = '';
            index++;
        }
    }

    if (index == 18) {
        status = true;
    } else {
        status = false;
    }
    return status;
}


function validateFormEventPost() {
    var status = false;
    var index = 0;
    ///LẤY BIẾN INPUT
    var name = document.getElementById("name");
    var priceTour = document.getElementById("priceTour");
    var priceAdult = document.getElementById("priceAdult");
    var priceBaby = document.getElementById("priceBaby");
    var priceChildren = document.getElementById("priceChildren");
    var toCity = document.getElementById("toCity");
    var fromCity = document.getElementById("fromCity");
    var region = document.getElementById("region");
    var event = document.getElementById("Event");
    var tranport = document.getElementById("tranport");
    var time = document.getElementById("time");
    var mainImage = document.getElementById("mainImage");
    var hinhCHINH = document.getElementById("hinhCHINH");
    var moTaTour = document.getElementById("moTaTour");
    var extraImage = document.querySelectorAll(".extraImage");
    var hinhPHU = document.querySelectorAll(".hinhPHU");


    ////LẤY BIẾN MESSAGE THÔNG BÁO
    var errorName = document.getElementById("errorName");
    var errorPriceTour = document.getElementById("errorPriceTour");
    var errorPriceAdult = document.getElementById("errorPriceAdult");
    var errorPriceBaby = document.getElementById("errorPriceBaby");
    var errorPriceChildren = document.getElementById("errorPriceChildren");
    var errorFromCity = document.getElementById("errorFromCity");
    var errorToCity = document.getElementById("errorToCity");
    var errorRegion = document.getElementById("errorRegion");
    var errorEvent = document.getElementById("errorEvent");
    var errorTranport = document.getElementById("errorTranport");
    var errorTime = document.getElementById("errorTime");
    var errorPrimaryImage = document.getElementById("errorPrimaryImage");
    var errorDescriptionTour = document.getElementById("errorDescriptionTour");

    ///--KIỂM TRA TÊN TOUR
    if (name.value == '') {
        text = `<i class="fas fa-exclamation-circle"></i>Vui lòng điền vào tên`;
        errorName.innerHTML = text;

    } else {
        errorName.innerHTML = '';

        index++;
    }
    ///---KIỂM TRA GIÁ TOUR
    if (priceTour.value == '') {
        text = `<i class="fas fa-exclamation-circle"></i>Vui lòng điền vào giá tour`;
        errorPriceTour.innerHTML = text;

    } else if (isNaN(priceTour.value)) {
        text = `<i class="fas fa-exclamation-circle"></i>Mệnh giá tiền phải là số`;
        errorPriceTour.innerHTML = text;

    } else {
        errorPriceTour.innerHTML = '';

        index++;
    }

    ///---KIỂM TRA GIÁ NGƯỜI LỚN
    if (priceAdult.value == '') {
        text = `<i class="fas fa-exclamation-circle"></i>Vui lòng điền vào giá người lớn`;
        errorPriceAdult.innerHTML = text;

    } else if (isNaN(priceAdult.value)) {
        text = `<i class="fas fa-exclamation-circle"></i>Mệnh giá tiền phải là số`;
        errorPriceAdult.innerHTML = text;

    } else {
        errorPriceAdult.innerHTML = '';

        index++;
    }

    ///---KIỂM TRA GIÁ TRẺ EM
    if (priceChildren.value == '') {
        text = `<i class="fas fa-exclamation-circle"></i>Vui lòng điền vào giá trẻ em`;
        errorPriceChildren.innerHTML = text;

    } else if (isNaN(priceChildren.value)) {
        text = `<i class="fas fa-exclamation-circle"></i>Mệnh giá tiền phải là số`;
        errorPriceChildren.innerHTML = text;

    } else {
        errorPriceChildren.innerHTML = '';

        index++;
    }

    ///---KIỂM TRA GIÁ EM BÉ
    if (priceBaby.value == '') {
        text = `<i class="fas fa-exclamation-circle"></i>Vui lòng điền vào giá em bé`;
        errorPriceBaby.innerHTML = text;

    } else if (isNaN(priceBaby.value)) {
        text = `<i class="fas fa-exclamation-circle"></i>Mệnh giá tiền phải là số`;
        errorPriceBaby.innerHTML = text;

    } else {
        errorPriceBaby.innerHTML = '';

        index++;
    }

    ///KIỂM TRA VÙNG
    if (region.value == '0') {
        text = `<i class="fas fa-exclamation-circle"></i>Vui lòng chọn vùng`;
        errorRegion.innerHTML = text;

    } else {
        errorRegion.innerHTML = '';

        index++;
    }

    ///KIỂM TRA ĐIỂM KHỜI HÀNH
    if (fromCity.value == '0') {
        text = `<i class="fas fa-exclamation-circle"></i>Vui lòng chọn khởi hành`;
        errorFromCity.innerHTML = text;

    } else {
        errorFromCity.innerHTML = '';

        index++;
    }

    ////KIỂM TRA ĐIỂM ĐẾN
    if (toCity.value == "") {
        text = `<i class="fas fa-exclamation-circle"></i>Vui lòng chọn điểm đến`;
        errorToCity.innerHTML = text;

    } else {
        errorToCity.innerHTML = '';

        index++;
    }

    ////KIỂM TRA LOẠI EVENT
    if (event.value == "0") {
        text = `<i class="fas fa-exclamation-circle"></i>Vui lòng chọn loại sự kiện`;
        errorEvent.innerHTML = text;

    } else {
        errorEvent.innerHTML = '';

        index++;
    }

    ///KIỂM TRA PHƯƠNG TIỆN
    if (tranport.value == "0") {
        text = `<i class="fas fa-exclamation-circle"></i>Vui lòng chọn phương tiện`;
        errorTranport.innerHTML = text;

    } else {
        errorTranport.innerHTML = '';

        index++;
    }

    ///KIỂM TRA THỜI GIAN TOUR
    if (time.value == "") {
        text = `<i class="fas fa-exclamation-circle"></i>Vui lòng điền vào thời gian chuyến đi`;
        errorTime.innerHTML = text;

    } else {
        errorTime.innerHTML = '';

        index++;
    }

    ///KIỂM TRA MÔ TẢ TOUR
    // if(moTaTour.value == ""){
    //     text = `<i class="fas fa-exclamation-circle"></i>Vui lòng điền vào mô tả cho tour`;
    //     errorDescriptionTour.innerHTML = text;
    //     
    // }else{
    //     errorDescriptionTour.innerHTML = '';
    //     
    //     index++;
    // }

    ///KIỂM TRA HÌNH CHÍNH 
    if (mainImage.value == "" && hinhCHINH.value == "") {
        text = `<i class="fas fa-exclamation-circle"></i>Vui lòng chọn đầy đủ các hình`;
        errorPrimaryImage.innerHTML = text;

    } else {
        errorPrimaryImage.innerHTML = '';

        index++;
    }

    for (var i = 0; i < extraImage.length; i++) {
        if (extraImage[i].value == "" && hinhPHU[i].value == "") {
            text = `<i class="fas fa-exclamation-circle"></i>Vui lòng chọn đầy đủ các hình`;
            errorPrimaryImage.innerHTML = text;
        } else {
            errorPrimaryImage.innerHTML = '';
            index++;
        }
    }

    if (index == 18) {
        status = true;
    } else {
        status = false;
    }
    return status;
}

function changePass() {
    var status = false;
    var index = 0;
    var currentPass = document.forms["formChangePass"]["oldPassword"].value;
    var newPass = document.forms["formChangePass"]["newPassword"].value;
    var confirmPass = document.forms["formChangePass"]["confirmPassword"].value;

    var errorConfirmPass = document.getElementById("errorConfirmPass");
    var errorSpringPass = document.getElementById("errorSpringPass");
    var errorNewPass = document.getElementById("errorNewPass");
    var errorCurrentPass = document.getElementById("errorCurrentPass");

    if (currentPass == "") {

        text = '<i class="fas fa-exclamation-circle"></i>Vui lòng nhập vào mật khẩu hiện tại';
        errorCurrentPass.innerHTML = text;
        errorSpringPass.innerHTML = '';
    } else {
        errorCurrentPass.innerHTML = '';
        index++;
    }

    if (newPass == "") {
        text = '<i class="fas fa-exclamation-circle"></i>Vui lòng nhập vào mật khẩu mới';
        errorNewPass.innerHTML = text;
    } else {
        errorNewPass.innerHTML = '';
        index++;
    }

    if (newPass != confirmPass) {
        text = '<i class="fas fa-exclamation-circle"></i>Nhập lại mật khẩu không chính xác';
        errorConfirmPass.innerHTML = text;

    } else if (confirmPass == "") {
        text = '<i class="fas fa-exclamation-circle"></i>Vui lòng nhập lại mật khẩu';
        errorConfirmPass.innerHTML = text;

    } else {
        errorConfirmPass.innerHTML = '';
        index++;
    }

    if (index == 3) {
        status = true;
    } else {
        status = false;
    }
    return status;

}


function validateRegisterCustomer() {
    var status = false;
    var index = 0;
    var ktdb = /^[a-zA-Z0-9]+$/;
    var mailformat = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    //INPUT
    var username = document.forms["myFormCustomer"]["username"].value;
    var password = document.forms["myFormCustomer"]["password"].value;
    var confirmPassword = document.forms["myFormCustomer"]["confirmPassword"].value;
    var fullname = document.forms["myFormCustomer"]["fullname"].value;
    var phone = document.forms["myFormCustomer"]["phone"].value;
    var email = document.forms["myFormCustomer"]["email"].value;
    var address = document.forms["myFormCustomer"]["address"].value;
    //THONG BAO
    var errorUsername = document.getElementById("messnamekh");
    var errorCustUser = document.getElementById("errorCustUser");
    var errorEmail = document.getElementById("messemailkh");
    var errorCustEmail = document.getElementById("errorCustEmail");

    var errorPassword = document.getElementById("messpasskh");
    var errorConfirmPassword = document.getElementById("messconpasskh");
    var errorfullname = document.getElementById("messfullnamekh");
    var errorphone = document.getElementById("messphonekh");
    var erroraddress = document.getElementById("messaddresskh");

    if (username.trim() == "") {
        text = `<i class="fas fa-exclamation-circle"></i>Vui lòng nhập vào username`;
        errorUsername.innerHTML = text;
        errorCustUser.innerHTML = '';
    } else {
        if (ktdb.test(username)) {
            errorUsername.innerHTML = '';
            index++;
        } else {
            text = `<i class="fas fa-exclamation-circle"></i>Vui lòng không nhập ký tự đặt biệt`;
            errorUsername.innerHTML = text;
            errorCustUser.innerHTML = '';
        }
    }

    if (email.trim() == "") {
        text = `<i class="fas fa-exclamation-circle"></i>Vui lòng nhập vào email`;
        errorEmail.innerHTML = text;
        errorCustEmail.innerHTML = '';
    } else {
        if (mailformat.test(email)) {
            errorEmail.innerHTML = '';
            index++;
        } else {
            text = `<i class="fas fa-exclamation-circle"></i>Vui lòng nhập dung email`;
            errorEmail.innerHTML = text;
            errorCustEmail.innerHTML = '';
        }

    }

    if (password == "") {
        text = `<i class="fas fa-exclamation-circle"></i>Vui lòng nhập vào password`;
        errorPassword.innerHTML = text;
    } else {
        errorPassword.innerHTML = '';
        index++;
    }

    if (confirmPassword == "") {
        text = `<i class="fas fa-exclamation-circle"></i>Vui lòng nhập lai confirm Password`;
        errorConfirmPassword.innerHTML = text;
    } else {
        if (password != confirmPassword) {
            text = `<i class="fas fa-exclamation-circle"></i>Vui lòng nhập lai confirm Password chinh xac`;
            errorConfirmPassword.innerHTML = text;
        } else {
            errorConfirmPassword.innerHTML = '';
            index++;
        }

    }

    if (fullname.trim() == "") {
        text = `<i class="fas fa-exclamation-circle"></i>Vui lòng nhập vào fullname`;
        errorfullname.innerHTML = text;
    } else {
        errorfullname.innerHTML = '';
        index++;
    }
    if (phone.trim() == "") {
        text = `<i class="fas fa-exclamation-circle"></i>Vui lòng nhập vào phone`;
        errorphone.innerHTML = text;

    } else {
        if (phone.length > 8 && phone.length < 13) {
            errorphone.innerHTML = '';
            index++;
        } else {
            text = `<i class="fas fa-exclamation-circle"></i>Vui lòng nhập vào phone > 8 va be hon 13`;
            errorphone.innerHTML = text;
        }

    }
    if (address.trim() == "") {
        text = `<i class="fas fa-exclamation-circle"></i>Vui lòng nhập vào address`;
        erroraddress.innerHTML = text;
    } else {
        erroraddress.innerHTML = '';
        index++;
    }

    if (index == 7) {
        status = true;
    } else {
        alert("Vui lòng điền đúng các thông tin")
        status = false;
    }
    return status;

}


function validateRegisterEnter() {
    var status = false;
    var index = 0;
    var ktdb = /^[a-zA-Z0-9]+$/;
    var mailformat = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    //INPUT
    var username = document.forms["myFormEnter"]["username"].value;
    var password = document.forms["myFormEnter"]["password"].value;
    var confirmPassword = document.forms["myFormEnter"]["confirmPassword"].value;
    var fullname = document.forms["myFormEnter"]["fullname"].value;
    var phone = document.forms["myFormEnter"]["phone"].value;
    var email = document.forms["myFormEnter"]["email"].value;
    var address = document.forms["myFormEnter"]["address"].value;
    var nameAdmin = document.forms["myFormEnter"]["nameAdmin"].value;
    var taxCode = document.forms["myFormEnter"]["taxCode"].value;
    //THONG BAO
    var errorUsername = document.getElementById("messnamedn");
    var errorCustUser = document.getElementById("errorCustUser");
    var errorEmail = document.getElementById("messemaildn");
    var errorCustEmail = document.getElementById("errorCustEmail");

    var errorPassword = document.getElementById("messpassdn");
    var errorConfirmPassword = document.getElementById("messconpassdn");
    var errorfullname = document.getElementById("messfullnamedn");
    var errorphone = document.getElementById("messphonedn");
    var erroraddress = document.getElementById("messaddressdn");
    var errornameAdmin = document.getElementById("messnameAdmindn");
    var errortaxCode = document.getElementById("messtaxCodedn");

    if (username.trim() == "") {
        text = `<i class="fas fa-exclamation-circle"></i>Vui lòng nhập vào username`;
        errorUsername.innerHTML = text;
        errorCustUser.innerHTML = '';
    } else {
        if (ktdb.test(username)) {
            errorUsername.innerHTML = '';
            index++;
        } else {
            text = `<i class="fas fa-exclamation-circle"></i>Vui lòng không nhập ký tự đặt biệt`;
            errorUsername.innerHTML = text;
            errorCustUser.innerHTML = '';
        }
    }

    if (email.trim() == "") {
        text = `<i class="fas fa-exclamation-circle"></i>Vui lòng nhập vào email`;
        errorEmail.innerHTML = text;
        errorCustEmail.innerHTML = '';
    } else {
        if (mailformat.test(email)) {
            errorEmail.innerHTML = '';
            index++;
        } else {
            text = `<i class="fas fa-exclamation-circle"></i>Vui lòng nhập dung email`;
            errorEmail.innerHTML = text;
            errorCustEmail.innerHTML = '';
        }

    }

    if (password == "") {
        text = `<i class="fas fa-exclamation-circle"></i>Vui lòng nhập vào password`;
        errorPassword.innerHTML = text;
    } else {
        errorPassword.innerHTML = '';
        index++;
    }

    if (confirmPassword == "") {
        text = `<i class="fas fa-exclamation-circle"></i>Vui lòng nhập lai confirm Password`;
        errorConfirmPassword.innerHTML = text;
    } else {
        if (password != confirmPassword) {
            text = `<i class="fas fa-exclamation-circle"></i>Vui lòng nhập lai confirm Password chinh xac`;
            errorConfirmPassword.innerHTML = text;
        } else {
            errorConfirmPassword.innerHTML = '';
            index++;
        }

    }

    if (fullname.trim() == "") {
        text = `<i class="fas fa-exclamation-circle"></i>Vui lòng nhập vào fullname`;
        errorfullname.innerHTML = text;
    } else {
        errorfullname.innerHTML = '';
        index++;
    }
    if (phone.trim() == "") {
        text = `<i class="fas fa-exclamation-circle"></i>Vui lòng nhập vào phone`;
        errorphone.innerHTML = text;

    } else {
        if (phone.length > 8 && phone.length < 13) {
            errorphone.innerHTML = '';
            index++;
        } else {
            text = `<i class="fas fa-exclamation-circle"></i>Vui lòng nhập vào phone > 4 va be hon 13`;
            errorphone.innerHTML = text;
        }

    }
    if (address.trim() == "") {
        text = `<i class="fas fa-exclamation-circle"></i>Vui lòng nhập vào address`;
        erroraddress.innerHTML = text;
    } else {
        erroraddress.innerHTML = '';
        index++;
    }




    if (nameAdmin.trim() == "") {
        text = `<i class="fas fa-exclamation-circle"></i>Vui lòng nhập vào tên người quản lý`;
        errornameAdmin.innerHTML = text;
    } else {
        errornameAdmin.innerHTML = '';
        index++;
        if (taxCode.trim() == "") {
            text = `<i class="fas fa-exclamation-circle"></i>Vui lòng nhập vào mã số thuế`;
            errortaxCode.innerHTML = text;

        } else {
            if (taxCode.length > 8 && taxCode.length < 13) {
                errortaxCode.innerHTML = '';
                index++;
            } else {
                text = `<i class="fas fa-exclamation-circle"></i>Vui lòng nhập vào mã số thuế > 8 va be hon 13`;
                errortaxCode.innerHTML = text;
            }

        }
    }



    if (index == 9) {
        status = true;
    } else {
        alert("Vui lòng điền đúng các thông tin")
        status = false;
    }
    return status;

}