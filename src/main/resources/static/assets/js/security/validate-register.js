function validateregister() {
    namekh = document.getElementById("#namekh");
    passkh = document.getElementById("#passkh");
    conpasskh = document.getElementById("#conpasskh");

    var trangthai = false;
    messnamekh = document.getElementById("#messnamekh");
    messpasskh = document.getElementById("#messpasskh");
    messpassconkh = document.getElementById("#messpassconkh");



    if (namekh.value == '') {
        text = "Không được bỏ trống username";
        messnamekh.innerHTML = text;
        namekh.focus();
        trangthai = false;
    } else {
        messUser.innerHTML = '';
        trangthai = true;
    }
    if (passkh.value == '') {
        text = "Không được bỏ trống mật khẩu";
        messpasskh.innerHTML = text;
        passkh.focus();
        trangthai = false;
    } else {
        messpasskh.innerHTML = '';
        trangthai = true;
    }
    if (conpasskh.value == '') {
        text = "Không được bỏ trống xác nhận mật khẩu ";
        messpassconkh.innerHTML = text;
        conpasskh.focus();
        trangthai = false;
    } else {
        messpassconkh.innerHTML = '';
        trangthai = true;
    }
    var index = 0;

    if (index == 3) {
        trangthai = true;
    } else {
        trangthai = false;
    }
    return trangthai;
}