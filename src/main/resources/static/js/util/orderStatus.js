export const orderStatusToKR = (status) => {
    return orderStatus[status];
}
export const franchiseOrderStatusToKR = (status) => {
    return franchiseOrderStatus[status];
}

export const itemStatusToKR = (status) => {
    return itemStatus[status];
}
export const productStatusToKR = (status) => {
    return productStatus[status];
}

export const orderStatus = ['대기', '진행', '완료', '반려'];
export const itemStatus = ['진행', '완료'];

export const franchiseOrderStatus = ['미발주','대기', '진행', '완료', '반려'];
export const productStatus = ['미발주','대기','진행', '완료', '반려'];
