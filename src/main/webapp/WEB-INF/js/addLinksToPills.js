$(document).ready(function () {
    var pillUrl = new URL(window.location.href);
    pillUrl.searchParams.set("page", 1);
    pillUrl.searchParams.set("archived", false);
    $("#activeLink").attr("href", pillUrl.toString());
    pillUrl.searchParams.set("archived", true);
    $("#archivedLink").attr("href", pillUrl.toString());
});
