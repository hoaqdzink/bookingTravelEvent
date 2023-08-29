$(document).ready(function () {
    //GỌI HÀM LÊN THEO ID
    //ĐỐI CHIÉU VỚI ID / NAME Ở TRÊN
    $('#mainImage').change(function () {
        showImageThumbnail(this);
    });

    $("input[name='extraImage']").each(function (index) {
        $(this).change(function () {
            showImageThumbnailAll(this, index);
        });
    });
});
/////////////////////////HÀM VIẾT Ở DƯỚI/////////////
//HÀM ĐỂ HIỆN HÌNH ẢNH
function showImageThumbnail(fileInput) {
    file = fileInput.files[0];
    reader = new FileReader();
    reader.onload = function (e) {
        $('#thumbnailMainImage').attr('src', e.target.result);
    };
    reader.readAsDataURL(file);
}

//HÀM ĐỂ HIỆN HÌNH ẢNH
function showImageThumbnailAll(fileInput, index) {
    file = fileInput.files[0];
    reader = new FileReader();
    reader.onload = function (e) {
        $('#thumbnailExtraImage' + index).attr('src', e.target.result);
    };
    reader.readAsDataURL(file);
}