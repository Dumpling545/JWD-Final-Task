function translatePage(oldPage, oldSize, newSize){
    var newPage = Math.floor((oldPage - 1) * oldSize / newSize) + 1;
    return newPage;
}
$(document).ready(function () {
    var url = new URL(window.location.href);
    var oldPage = parseInt(url.searchParams.get("page"));
    if(isNaN(oldPage)){
        oldPage = 1;
    }
    var oldSize = parseInt(url.searchParams.get("resultsperpage"));
    if(isNaN(oldSize)){
        oldSize = 10;
    }
    url.searchParams.set("resultsperpage", 10);
    url.searchParams.set("page", translatePage(oldPage, oldSize, 10));
    $("#perpage10").attr("href", url.toString());
    url.searchParams.set("resultsperpage", 20);
    url.searchParams.set("page", translatePage(oldPage, oldSize, 20));
    $("#perpage20").attr("href", url.toString());
    url.searchParams.set("resultsperpage", 30);
    url.searchParams.set("page", translatePage(oldPage, oldSize, 30));
    $("#perpage30").attr("href", url.toString());
    url.searchParams.set("resultsperpage", 40);
    url.searchParams.set("page", translatePage(oldPage, oldSize, 40));
    $("#perpage40").attr("href", url.toString());
});