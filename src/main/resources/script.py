import json
import numpy as np
import datetime
import random
menu = [
  {
    "id": 1,
    "menu": "허니콤보",
    "price": 23000
  },
  {
    "id": 2,
    "menu": "레드콤보",
    "price": 23000
  },
  {
    "id": 3,
    "menu": "허니오리지날（한마리）",
    "price": 19000
  },
  {
    "id": 4,
    "menu": "레드윙",
    "price": 23000
  },
  {
    "id": 5,
    "menu": "반반윙",
    "price": 23000
  },
  {
    "id": 6,
    "menu": "블랙시크릿콤보",
    "price": 23000
  },
  {
    "id": 7,
    "menu": "블랙시크릿오리지날（한마리）",
    "price": 20000
  },
  {
    "id": 8,
    "menu": "블랙시크릿순살",
    "price": 23000
  },
  {
    "id": 9,
    "menu": "허니콤보",
    "price": 23000
  },
  {
    "id": 10,
    "menu": "허니순살",
    "price": 23000
  },
  {
    "id": 11,
    "menu": "레드오리지날（한마리）",
    "price": 20000
  },
  {
    "id": 12,
    "menu": "레드콤보",
    "price": 23000
  },
  {
    "id": 13,
    "menu": "레드윙",
    "price": 23000
  },
  {
    "id": 14,
    "menu": "레드스틱",
    "price": 23000
  },
  {
    "id": 15,
    "menu": "레드순살",
    "price": 23000
  },
  {
    "id": 16,
    "menu": "교촌오리지날（한마리）",
    "price": 19000
  },
  {
    "id": 17,
    "menu": "교촌콤보",
    "price": 22000
  },
  {
    "id": 18,
    "menu": "교촌윙",
    "price": 22000
  },
  {
    "id": 19,
    "menu": "교촌스틱",
    "price": 22000
  },
  {
    "id": 20,
    "menu": "교촌순살",
    "price": 22000
  },
  {
    "id": 21,
    "menu": "레블반반콤보",
    "price": 23000
  },
  {
    "id": 22,
    "menu": "반반오리지날（한마리）",
    "price": 20000
  },
  {
    "id": 23,
    "menu": "반반콤보",
    "price": 23000
  },
  {
    "id": 24,
    "menu": "반반윙",
    "price": 23000
  },
  {
    "id": 25,
    "menu": "반반스틱",
    "price": 23000
  },
  {
    "id": 26,
    "menu": "반반순살",
    "price": 23000
  },
  {
    "id": 27,
    "menu": "레허반반순살",
    "price": 23000
  },
  {
    "id": 28,
    "menu": "시그니처순살세트",
    "price": 33000
  },
  {
    "id": 29,
    "menu": "리얼후라이드",
    "price": 20000
  },
  {
    "id": 30,
    "menu": "살살후라이드",
    "price": 20000
  },
  {
    "id": 31,
    "menu": "살살후라이드미니",
    "price": 7000
  },
  {
    "id": 32,
    "menu": "파채소이살살",
    "price": 19000
  },
  {
    "id": 33,
    "menu": "블랙시크릿콤보[S]",
    "price": 14000
  },
  {
    "id": 34,
    "menu": "블랙시크릿순살[S]",
    "price": 14000
  },
  {
    "id": 35,
    "menu": "허니순살[S]",
    "price": 13000
  },
  {
    "id": 36,
    "menu": "레드순살[S]",
    "price": 13000
  },
  {
    "id": 37,
    "menu": "교촌순살[S]",
    "price": 12000
  },
  {
    "id": 38,
    "menu": "교촌콤보[S]",
    "price": 12000
  },
  {
    "id": 39,
    "menu": "레드콤보[S]",
    "price": 13000
  },
  {
    "id": 40,
    "menu": "레드윙[S]",
    "price": 13000
  },
  {
    "id": 41,
    "menu": "교촌윙[S]",
    "price": 12000
  },
  {
    "id": 42,
    "menu": "레드스틱[S]",
    "price": 13000
  },
  {
    "id": 43,
    "menu": "교촌스틱[S]",
    "price": 12000
  },
  {
    "id": 44,
    "menu": "옥수수볼",
    "price": 5000
  },
  {
    "id": 45,
    "menu": "달걀듬뿍볶음밥",
    "price": 4000
  },
  {
    "id": 46,
    "menu": "의성마늘볶음밥",
    "price": 4000
  },
  {
    "id": 47,
    "menu": "포테이토앤칩스（점보팩）",
    "price": 6500
  },
  {
    "id": 48,
    "menu": "국물맵떡",
    "price": 9000
  },
  {
    "id": 49,
    "menu": "웨지감자",
    "price": 4000
  },
  {
    "id": 50,
    "menu": "칩카사바",
    "price": 2000
  },
  {
    "id": 51,
    "menu": "퐁듀치즈볼（6ea）",
    "price": 6000
  },
  {
    "id": 52,
    "menu": "퐁듀치즈볼（3ea）",
    "price": 3500
  },
  {
    "id": 53,
    "menu": "고르곤치즈볼（6ea）",
    "price": 6000
  },
  {
    "id": 54,
    "menu": "고르곤치즈볼（3ea）",
    "price": 3500
  },
  {
    "id": 55,
    "menu": "쫀드칸꽈배기（시나몬슈가）",
    "price": 4000
  },
  {
    "id": 56,
    "menu": "닭갈비볶음밥",
    "price": 4000
  },
  {
    "id": 57,
    "menu": "샐러드",
    "price": 5000
  },
  {
    "id": 58,
    "menu": "레드시크릿볶음면컵（비조리）",
    "price": 2300
  }
]
# 더미 데이터 생성
np.random.seed(42)
def create_random_order(min_price, maxp):
    order = []
    total_price = 0
    max_price = np.random.normal(loc=maxp, scale=20000, size=1)
    while True:
        choice = np.random.choice(menu)
        if choice in order:
           continue
        if max_price >= total_price + choice['price'] >= min_price:
            order.append(choice)
            total_price += choice['price']
        else:
            break
    if(total_price == 0):
        return create_random_order(min_price, maxp)
    ids = []
    menus = []
    prices = []
    for i in order:
        ids.append(i["id"])
        menus.append(i["menu"])
        prices.append(i["price"])
    return ids, menus, prices

def createDataa(franchiseId,current,count,maxPrice):
    ids, menus, prices = create_random_order(14000, maxPrice)
    data = []

    for (index,menu ) in enumerate(menus):
        data.append({
            "sales":{
              "id": count,
              "price": int(np.sum(prices)),
              "salesDate": current.strftime("%Y-%m-%d %H:%M:%S"),
              "franchise": {"id": franchiseId},
            },
            "menu":{
              "id": ids[index],
              "menu": menu,
              "price": prices[index],
            },
            "price":prices[index],
            "quantity": 1,
        })
    return data
# DataFrame 생성
franchise = [
    # 1098,
    # 1101
    #         ,1104
    #         ,1107
    #         ,1110
    #         1113
    #         ,1116
    #         ,1119,
    #     1122
    #     ,1125
    #     1128
    #     ,1131
    #     ,1134
    #     ,1137
    #     ,1140
        1143
  ,1152
]
temp =[
      ]
result = []
count = 144568
for id in franchise:
    current = datetime.datetime(2024, 1, 1, 9)
    for i in range(180):
        if(current.weekday() > 3):
            for j in range(random.choice(range(60,80))):
                data = createDataa(id,current,count,int(random.choice(range(16000, 25000))))
                current += datetime.timedelta(minutes=5)
                for k in data:
                  result.append(k)
                count += 1
        else:
            for j in range(random.choice(range(35,50))):
                data = createDataa(id,current,count,int(random.choice(range(16000, 25000))))
                current += datetime.timedelta(minutes=5)
                for k in data:
                  result.append(k)
                count += 1
        current += datetime.timedelta(days=1)
        current = current.replace(hour=9, minute=0)
print(json.dumps(result, ensure_ascii=False))
# for k in result:
#   print(json.dumps(k, ensure_ascii=False))