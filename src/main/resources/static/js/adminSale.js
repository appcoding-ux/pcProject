$(function(){
    
    // 최근 일주일 매출 그래프
    let recentSale = $('#recentSales');

    let orderDay = [];

    let daySales = [];

    let orderData = $('.orderData');

    for(let i = 0; i < orderData.length; i++){
        let day = orderData.eq(i).find('input[name="orderDay"]').val();
        let sales = orderData.eq(i).find('input[name="daySales"]').val();

        orderDay.push(day);
        daySales.push(sales);
    }

    let chartRecentSale = new Chart(recentSale , {
        type: 'line',
        data : {
            labels : orderDay, // 라벨 배열
            datasets : [{
                label : '최근 일주일 매출내역', //차트 라벨명
                data : daySales, // 데이터 배열,
                backgroundColor : [
                    'rgba(255, 99, 132, 0.2)',
                    'rgba(54, 162, 235, 0.2)',
                    'rgba(255, 206, 86, 0.2)',
                    'rgba(75, 192, 192, 0.2)',
                    'rgba(153, 102, 255, 0.2)',
                    'rgba(255, 159, 64, 0.2)',
                    'rgba(82, 76, 125, 0.2)',
                    'rgba(14, 230, 64, 0.2)'
                ],

                borderColor : [
                    'rgba(255, 99, 132, 1)',
                    'rgba(54, 162, 235, 1)',
                    'rgba(255, 206, 86, 1)',
                    'rgba(75, 192, 192, 1)',
                    'rgba(153, 102, 255, 1)',
                    'rgba(255, 159, 64, 1)',
                    'rgba(82, 76, 125, 1)',
                    'rgba(14, 230, 64, 1)'
                ],

                borderWidth : 1
            }]
        },
        options : {
            responsive: false,
            scales: {
                y : {
                    suggestedMin: 0
                }
            }
        }
    });

    // 카테고리별 판매 갯수
    // 최근 일주일 매출 그래프
    let categorySale = $('#categorySales');

    let categoryName = [];

    let countArr = [];

    let categorySalesData = $('.categorySalesData');

    for(let i = 0; i < categorySalesData.length; i++){
        let category = categorySalesData.eq(i).find('input[name="category"]').val();
        let count = categorySalesData.eq(i).find('input[name="count"]').val();

        categoryName.push(category);
        countArr.push(count);
    }

    let chartCategorySales = new Chart(categorySale , {
        type: 'bar',
        data : {
            labels : categoryName, // 라벨 배열
            datasets : [{
                label : '카테고리별 판매 갯수', //차트 라벨명
                data : countArr, // 데이터 배열,
                backgroundColor : [
                    'rgba(255, 99, 132, 0.2)',
                    'rgba(54, 162, 235, 0.2)',
                    'rgba(255, 206, 86, 0.2)',
                    'rgba(75, 192, 192, 0.2)',
                    'rgba(153, 102, 255, 0.2)',
                    'rgba(255, 159, 64, 0.2)',
                ],

                borderColor : [
                    'rgba(255, 99, 132, 1)',
                    'rgba(54, 162, 235, 1)',
                    'rgba(255, 206, 86, 1)',
                    'rgba(75, 192, 192, 1)',
                    'rgba(153, 102, 255, 1)',
                    'rgba(255, 159, 64, 1)',
                ],

                borderWidth : 1
            }]
        },
        options : {
            responsive: false,
            scales: {
                y : {
                    suggestedMin: 0
                }
            }
        }
    });

    $('.manySalesFoodBox').hover(function(){
        $(this).find('.manySalesFoodHoverBox').stop().slideToggle();
    })
});