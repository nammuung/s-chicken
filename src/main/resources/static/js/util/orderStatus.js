export const orderStatusToKR = (status) => {
    return orderStatus[status];
}

export const itemStatusToKR = (status) => {
    return itemStatus[status];
}

export const orderStatus = ['대기', '진행', '완료', '반려'];
export const itemStatus = ['진행', '완료'];
