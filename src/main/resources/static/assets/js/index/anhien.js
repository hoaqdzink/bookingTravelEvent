$(function() {
    let maxList = document.querySelectorAll("#slideShow1").length;
    let pageSize = 6;
    let totalPage = Math.ceil(maxList / pageSize);
    let currentPage = 1;
    let start = 0;
    let end = pageSize;

    function show() {
        $(" #slideShow1").each(function(index, slide) {
            if (index >= start && index < end) {
                $(slide).show();
            } else {
                $(slide).hide();
            }
        })
    }
    show();

    function runPage() {
        if (currentPage < 1) {
            currentPage = totalPage;
        } else if (currentPage > totalPage) {
            currentPage = 1;
        }
        start = (currentPage - 1) * pageSize;
        end = currentPage * pageSize;
    }

    $("#prev").click(function() {
        currentPage--;
        runPage();
        show();
    })

    $("#next").click(function() {
        currentPage++;
        runPage();
        show();
    })


    //Tour show
    let maxListTour = document.querySelectorAll(" #slideShow2").length;
    let pageSizeTour = 4;
    let totalPageTour = Math.ceil(maxListTour / pageSizeTour);
    let currentPageTour = 1;
    let startTour = 0;
    let endTour = pageSizeTour;


    function showTour() {
        $(" #slideShow2").each(function(indexTour, slideTour) {
            if (indexTour >= startTour && indexTour < endTour) {
                $(slideTour).show();
            } else {
                $(slideTour).hide();
            }
        })
    }
    showTour();

    function runPageTour() {
        if (currentPageTour < 1) {
            currentPageTour = totalPageTour;
        } else if (currentPageTour > totalPageTour) {
            currentPageTour = 1;
        }
        startTour = (currentPageTour - 1) * pageSizeTour;
        endTour = currentPageTour * pageSizeTour;
    }

    $("#prevTour").click(function() {
        currentPageTour--;

        runPageTour();
        showTour();
    })

    $("#nextTour").click(function() {
        currentPageTour++;

        runPageTour();
        showTour();
    })



    //show Event


    let maxListEvent = document.querySelectorAll(" #slideShow3").length;
    let pageSizeEvent = 4;
    let totalPageEvent = Math.ceil(maxListEvent / pageSizeEvent);
    let currentPageEvent = 1;
    let startEvent = 0;
    let endEvent = pageSizeEvent;

    function showEvent() {
        $(" #slideShow3").each(function(indexEvent, slideEvent) {
            if (indexEvent >= startEvent && indexEvent < endEvent) {
                $(slideEvent).show();
            } else {
                $(slideEvent).hide();
            }
        })
    }
    showEvent();

    function runPageEvent() {
        if (currentPageEvent < 1) {
            currentPageEvent = totalPageEvent;
        } else if (currentPageEvent > totalPageEvent) {
            currentPageEvent = 1;
        }
        startEvent = (currentPageEvent - 1) * pageSizeEvent;
        endEvent = currentPageEvent * pageSizeEvent;
    }

    $("#prevEvent").click(function() {
        currentPageEvent--;

        runPageEvent();
        showEvent();
    })

    $("#nextEvent").click(function() {
        currentPageEvent++;

        runPageEvent();
        showEvent();
    })
})