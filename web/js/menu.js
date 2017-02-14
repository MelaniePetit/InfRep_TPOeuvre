/**
 * Created by jeremy on 08/02/2017.
 */

$(document).ready(function(){
    $('#circleMenu').click(function () {
        if( $(this).hasClass('active'))
        {
            $(this).removeClass('active');
            // $(this).text('<i class="fa fa-bars"></i>');
            $('#overlay').removeClass('active');

            $('.menu-list .menu').each(function(){
                $(this).removeClass('active');
            });
        }
        else
        {
            $(this).addClass('active');
            // $(this).text('x');
            $('#overlay').addClass('active');

            $('.menu-list .menu').each(function(){
                $(this).addClass('active');
            });
        }
    });

    $('#menu-toggle').click(function(){
        $(this).toggleClass('open');
    })
});