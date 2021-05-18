<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<head>
    <style>
        .carousel {
            position: relative;
        }

        .carousel-inner {
            position: relative;
            width: 100%;
            overflow: hidden
        }

        .carousel-item {
            position: relative;
            display: none;
            -ms-flex-align: center;
            align-items: center;
            width: 100%;
            -webkit-backface-visibility: hidden;
            backface-visibility: hidden;
            -webkit-perspective: 1000px;
            perspective: 1000px;
        }

        .carousel-item-next,
        .carousel-item-prev,
        .carousel-item.active {
            display: block;
            transition: -webkit-transform .6s ease;
            transition: transform .6s ease;
            transition: transform .6s ease, -webkit-transform .6s ease
        }

        @media screen and (prefers-reduced-motion:reduce) {

            .carousel-item-next,
            .carousel-item-prev,
            .carousel-item.active {
                transition: none
            }
        }

        .carousel-item-next,
        .carousel-item-prev {
            position: absolute;
            top: 0
        }

        .carousel-item-next.carousel-item-left,
        .carousel-item-prev.carousel-item-right {
            -webkit-transform: translateX(0);
            transform: translateX(0)
        }

        /* @supports ((-webkit-transform-style:preserve-3d) or (transform-style:preserve-3d)) {

            .carousel-item-next.carousel-item-left,
            .carousel-item-prev.carousel-item-right {
                -webkit-transform: translate3d(0, 0, 0);
                transform: translate3d(0, 0, 0)
            }
        }

        .active.carousel-item-right,
        .carousel-item-next {
            -webkit-transform: translateX(100%);
            transform: translateX(100%)
        }

        @supports ((-webkit-transform-style:preserve-3d) or (transform-style:preserve-3d)) {

            .active.carousel-item-right,
            .carousel-item-next {
                -webkit-transform: translate3d(100%, 0, 0);
                transform: translate3d(100%, 0, 0)
            }
        } */

        /* .active.carousel-item-left,
        .carousel-item-prev {
            -webkit-transform: translateX(-100%);
            transform: translateX(-100%)
        } */

       /*  @supports ((-webkit-transform-style:preserve-3d) or (transform-style:preserve-3d)) {

            .active.carousel-item-left,
            .carousel-item-prev {
                -webkit-transform: translate3d(-100%, 0, 0);
                transform: translate3d(-100%, 0, 0)
            }
        }
        .carousel-inner img {width: 70%;height: 70%;} */

       /*  .carousel-fade .carousel-item {
            opacity: 0;
            transition-duration: .6s;
            transition-property: opacity
        }

        .carousel-fade .carousel-item-next.carousel-item-left,
        .carousel-fade .carousel-item-prev.carousel-item-right,
        .carousel-fade .carousel-item.active {
            opacity: 1
        }

        .carousel-fade .active.carousel-item-left,
        .carousel-fade .active.carousel-item-right {
            opacity: 0
        }

        .carousel-fade .active.carousel-item-left,
        .carousel-fade .active.carousel-item-prev,
        .carousel-fade .carousel-item-next,
        .carousel-fade .carousel-item-prev,
        .carousel-fade .carousel-item.active {
            -webkit-transform: translateX(0);
            transform: translateX(0)
        } */
/* 
        @supports ((-webkit-transform-style:preserve-3d) or (transform-style:preserve-3d)) {

            .carousel-fade .active.carousel-item-left,
            .carousel-fade .active.carousel-item-prev,
            .carousel-fade .carousel-item-next,
            .carousel-fade .carousel-item-prev,
            .carousel-fade .carousel-item.active {
                -webkit-transform: translate3d(0, 0, 0);
                transform: translate3d(0, 0, 0)
            }
        } */

        .carousel-control-next,
        .carousel-control-prev {
            position: absolute;
            top: 0;
            bottom: 0;
            display: -ms-flexbox;
            display: flex;
            -ms-flex-align: center;
            align-items: center;
            -ms-flex-pack: center;
            justify-content: center;
            color: #fff;
            text-align: center;
            opacity: .5
        }
        
        .carousel-control-prev {
            width: 2%;
        }

        .carousel-control-next:focus,
        .carousel-control-next:hover,
        .carousel-control-prev:focus,
        .carousel-control-prev:hover {
            color: #fff;
            text-decoration: none;
            outline: 0;
            opacity: .9
        }

        .carousel-control-prev {
            left: 1%;
        }

        .carousel-control-next {
            right: 1%;
        }

        .carousel-control-next-icon,
        .carousel-control-prev-icon {
            display: inline-block;
            width: 20px;
            height: 20px;
            background: transparent no-repeat center center;
            background-size: 100% 100%
        }

        .carousel-control-prev-icon {
            background-image: url("data:image/svg+xml;charset=utf8,%3Csvg xmlns='http://www.w3.org/2000/svg' fill='%23fff' viewBox='0 0 8 8'%3E%3Cpath d='M5.25 0l-4 4 4 4 1.5-1.5-2.5-2.5 2.5-2.5-1.5-1.5z'/%3E%3C/svg%3E")
        }

        .carousel-control-next-icon {
            background-image: url("data:image/svg+xml;charset=utf8,%3Csvg xmlns='http://www.w3.org/2000/svg' fill='%23fff' viewBox='0 0 8 8'%3E%3Cpath d='M2.75 0l-1.5 1.5 2.5 2.5-2.5 2.5 1.5 1.5 4-4-4-4z'/%3E%3C/svg%3E")
        }

        .carousel-indicators {
            position: absolute;
            right: 0;
            bottom: 10px;
            left: 0;
            z-index: 15;
            display: -ms-flexbox;
            display: flex;
            -ms-flex-pack: center;
            justify-content: center;
            padding-left: 0;
            margin-right: 15%;
            margin-left: 15%;
            list-style: none
        }

        .carousel-indicators li {
            position: relative;
            -ms-flex: 0 1 auto;
            flex: 0 1 auto;
            width: 30px;
            height: 3px;
            margin-right: 3px;
            margin-left: 3px;
            text-indent: -999px;
            cursor: pointer;
            background-color: rgba(255, 255, 255, .5)
        }

        .carousel-indicators li::before {
            position: absolute;
            top: -10px;
            left: 0;
            display: inline-block;
            width: 100%;
            height: 10px;
            content: ""
        }

        .carousel-indicators li::after {
            position: absolute;
            bottom: -10px;
            left: 0;
            display: inline-block;
            width: 100%;
            height: 10px;
            content: ""
        }

        .carousel-indicators .active {
            background-color: #fff
        }

        .carousel-caption {
            position: absolute;
            right: 15%;
            bottom: 20px;
            left: 15%;
            z-index: 10;
            padding-top: 20px;
            padding-bottom: 20px;
            color: #fff;
            text-align: center
        }

        .align-baseline {
            vertical-align: baseline !important
        }
      
    </style>

</head>

<body>




  <div id="wayuparty_banner_slide" class="carousel slide" data-ride="carousel"> 

    <ul class="carousel-indicators">
      <li data-target="#wayuparty_banner_slide" data-slide-to="0" class="active"></li>
      <li data-target="#wayuparty_banner_slide" data-slide-to="1"></li>
      <li data-target="#wayuparty_banner_slide" data-slide-to="2"></li>
    </ul>
    
    <div class="carousel-inner">
      <div class="carousel-item active"> <img src="/resources/img/banners/web_Banner_1.jpg" alt="Web Banner 1" 
      		style="width:100%" onclick="javascript:window.location.href = '${Wayuparty_appUrl}/clubEvents?vendorUUID=0LUhvH54'"> 
      </div>
      
      <div class="carousel-item"> <img src="/resources/img/banners/web_Banner_2.jpg" alt="Web Banner 2" 
      		style="width:100%" onclick="javascript:window.location.href = '${Wayuparty_appUrl}/ws/bookService?vendorUUID=0LUhvH54'"> 
      </div>
      
      <div class="carousel-item"> <img src="/resources/img/banners/web_Banner_3.jpg" alt="Web Banner 3" 
      		style="width:100%" onclick="javascript:window.location.href = '${Wayuparty_appUrl}/ws/bookService?vendorUUID=0LUhvH54'"> 
      </div>
      
    </div>
    
    <!-- Left and right controls --> 
    <a class="carousel-control-prev" href="#wayuparty_banner_slide" data-slide="prev"> <span class="carousel-control-prev-icon"></span> </a> <a class="carousel-control-next" href="#wayuparty_banner_slide" data-slide="next"> <span class="carousel-control-next-icon"></span> </a> </div>


 
    <script>
        const counters = document.querySelectorAll('.counter');
        const speed = 50;
        counters.forEach(counter => {
            const updateCount = () => {
                const target = +counter.getAttribute('data-target');
                const count = +counter.innerText;
                const inc = target / speed;

                if (count < target) {
                    // Add inc to count and output in counter
                    counter.innerText = count + inc;
                    // Call function every ms
                    setTimeout(updateCount, 10);
                } else {
                    counter.innerText = target;
                }
            };
            updateCount();
        });
        $(document).ready(function () {
            var owl = $('.owl-carousel');
            owl.owlCarousel({
                items: 4,
                loop: true,
                margin: 10,
                autoplay: true,
                autoplayTimeout: 1000,
                autoplayHoverPause: true,
                nav: true,
                responsiveClass: true,
                responsive: {
                    0: {
                        items: 1,
                        nav: true
                    },
                    600: {
                        items: 3,
                        nav: false
                    },
                    1000: {
                        items: 4,
                        nav: true,
                        loop: true
                    }
                }
            });
        })
    </script>
</body>