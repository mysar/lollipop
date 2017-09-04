$(function() {
    $("#oa-manage-li").addClass("active");
    $("#oa-list-li").addClass("active");
    $("#oa-list-li").parent().addClass("in");
    initPage();
});


function initPage(){
    var page = $("#current-page").val();
    if (page == null || page == 0) {
        page = 1;
    }
    $.ajax({
        url: '/initPage',
        data: 'page=' + page,
        success: function (data) {
            $("#total-num").text(data.totalCount);
            $("#total-page").text(data.totalPageNum);
            $("#current-page").text(data.page);
            if (data.totalCount > 0) {

                $.jqPaginator('#pagination', {
                    totalPages: data.totalPageNum,
                    totalCounts: data.totalCount,
                    visiblePages: 5,
                    currentPage: data.page,
                    prev: '<li class="prev"><a href="javascript:;">Previous</a></li>',
                    next: '<li class="next"><a href="javascript:;">Next</a></li>',
                    page: '<li class="page"><a href="javascript:;">{{page}}</a></li>',
                    onPageChange: function (num, type) {
                        $("#current-page").text(num);
                        loadPartnerList();
                    }
                });
            }else {
                loadPartnerList();
            }
        }
    });
}


// 跳转分页
function toPage(page) {
    $("#page").val(page);
    loadPartnerList();
}

// 加载菜单列表
function loadPartnerList() {
    var param = $("#keyword").val();
    // 收集参数
    var page = $("#now").val();
    if (isEmpty(page) || page == 0) {
        page = 1;
    }

    // 查询列表
    $.ajax({
        url: '/admin/webdto/load',
        data: 'page=' + page+"&param="+param,
        success: function (data) {
            $("#dataList").html(data);
        }
    });

}


// 搜索
$("#oa-search").on('click',function () {
    loadPartnerList();

});
