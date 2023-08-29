$(function() {
    let maxList = document.querySelectorAll("#vaoMore #moreShow").length;

    let pageSize = 8;
    let totalPage = Math.ceil(maxList / pageSize);
    let currentPage = 1;
    let start = 0;
    let end = pageSize;
    if (currentPage == totalPage) {
        document.getElementById("showMore").style.display = "none";
    }

    function show() {
        $("#vaoMore #moreShow").each(function(index, tour) {
            if (index >= start && index < end) {
                $(tour).show();
            } else {
                $(tour).hide();
            }
        })
    }
    show();
    $("#showMore").click(function() {

        currentPage++;
        if (currentPage == totalPage) {
            document.getElementById("showMore").style.display = "none";
        }
        start = 0;
        end = currentPage * pageSize;
        show();
    })
})