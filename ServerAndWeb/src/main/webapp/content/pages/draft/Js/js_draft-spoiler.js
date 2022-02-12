$(document).ready(function () {
    $('.content__title').click(function (event) {
        if ($('.content').hasClass('one')) {
            $('.content__title').not($(this)).removeClass('active');
            $('.content__element').not($(this).next()).slideUp(300);
        }
        $(this).toggleClass('active').next().slideToggle(300);
    });
});
