import {getSalesList} from "../api/sales.js";
const salesListContainer = document.getElementById("salesListContainer");
Array.prototype.slice.call(document.querySelector("select"))
    .forEach(options => {
        if(options.value == '${pager.kind}') options.selected = true;
    });
async function loadSales(){
    const result = await getSalesList(articlePage);
    renderTable(result.data);
}
async function renderTable(data){
    let innerHtml = "";
    data.forEach((sale) => {
        let menus = sale.details.map(item =>
            item.menu.menu+"("+item.menu.price.toLocaleString("ko-KR")+")"
        )
        innerHtml += `
                <tr>
                    <td>${sale.id}</td>
                    <td class="text-wrap">${menus.join(", ")}</td>
                    <td>${sale.price.toLocaleString("ko-KR")}원</td>
                    <td>${dayjs(sale.salesDate).format("YYYY-MM-DD HH:mm")}</td>
                </tr>
            `;
    })
    salesListContainer.insertAdjacentHTML("beforeend", innerHtml);
    $articleEnd = salesListContainer.children[salesListContainer.children.length - 2];
    if (data.length < 10) return;
    articleObserver.observe($articleEnd);
}
let options = {
    threshold: 0,
};
let articlePage = 1;
const $articleResult = document.querySelector("#salesListContainer");
let $articleEnd;
const articleCallback = (entries, observer) => {
    entries.forEach(async (entry) => {
        if (entry.isIntersecting) {
            articlePage++;
            observer.unobserve($articleEnd);
            loadSales();
        }
    });
};
const articleObserver = new IntersectionObserver(articleCallback, options);
(async function(){
    await loadSales();
})();

