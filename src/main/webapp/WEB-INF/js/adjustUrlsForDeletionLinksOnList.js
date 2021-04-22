var numberOfObeservedClasses = 3;
$(document).ready(function () {
    var currentUrl = new URL(window.location.href);
    var page = parseInt(currentUrl.searchParams.get("page"));
    var workaround = "http://example.com/";
    if(!isNaN(page) && page > 1){
        for (var i = 1; i <= numberOfObeservedClasses; i++) {
            var observedClass = ".url-adjustment-object-" + i;
            var targetClass = ".url-adjustment-target-" + i;
            if ($(observedClass).length == 1) {
                $(targetClass).each(function () {
                    var url = new URL($(this).attr("href"), workaround);
                    var previousPageUrl = new URL(window.location.href);
                    previousPageUrl.searchParams.set("page", page - 1);
                    url.searchParams.set("returnurl", previousPageUrl.href);
                    url.searchParams.set("cancelurl", window.location.href);
                    $(this).attr("href", url.toString().replace(workaround, ""));
                });
            }
        }
    }
    
});