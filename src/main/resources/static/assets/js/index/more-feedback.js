$(function(){
    let maxList = document.querySelectorAll("#vaoFeedback #danhGiaBai").length;

    let pageSize = 5;
    let totalPage = Math.ceil(maxList/pageSize);
    let currentPage = 1;
    let start = 0;
    let end = pageSize;
    if(currentPage == totalPage || totalPage == 0){
        document.getElementById("xemThemDanhGia").style.display = "none";
    }
    function show(){
        $("#vaoFeedback #danhGiaBai").each(function(index, feedback){
            if(index >= start && index < end){
                $(feedback).show();
            }else{
                $(feedback).hide();
            }
        })
    }
    show();
    $("#xemThemDanhGia").click(function(){
        currentPage++;
        if(currentPage == totalPage){
            document.getElementById("xemThemDanhGia").style.display = "none";
        }
        start = 0;
        end = currentPage * pageSize;
        show();
    })
})