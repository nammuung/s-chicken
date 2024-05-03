import pandas as pd
import numpy as np
import json
#
# # 설정: 5개의 가맹점, 30일간의 데이터
# num_stores = 5
# num_days = 30
# dates = pd.date_range(start="2023-04-01", periods=num_days, freq='D')
#
# # 더미 데이터 생성
# np.random.seed(42)  # 결과 일관성을 위해
# data = {
#     "id": np.random.choice(range(1, num_stores + 1), size=num_days * num_stores),
#     "date": np.tile(dates, num_stores),
#     "sales": np.round(np.random.normal(loc=1500000, scale=500000, size=num_days * num_stores), -3)  # 평균 150만, 표준편차 50만
# }
#
# # DataFrame 생성
# df_sales = pd.DataFrame(data)
#
# json_output = df_sales.to_json(orient='records', date_format='iso')
# print(json_output)

menu = [
         {"menu": "허니콤보", "price": "23000"},
         {"menu": "레드콤보", "price": "23000"},
         {"menu": "허니오리지날（한마리）", "price": "19000"},
         {"menu": "레드윙", "price": "23000"},
         {"menu": "반반윙", "price": "23000"},
         {"menu": "블랙시크릿콤보", "price": "23000"},
         {"menu": "블랙시크릿오리지날（한마리）", "price": "20000"},
         {"menu": "블랙시크릿순살", "price": "23000"},
         {"menu": "허니콤보", "price": "23000"},
         {"menu": "허니순살", "price": "23000"},
         {"menu": "레드오리지날（한마리）", "price": "20000"},
         {"menu": "레드콤보", "price": "23000"},
         {"menu": "레드윙", "price": "23000"},
         {"menu": "레드스틱", "price": "23000"},
         {"menu": "레드순살", "price": "23000"},
         {"menu": "교촌오리지날（한마리）", "price": "19000"},
         {"menu": "교촌콤보", "price": "22000"},
         {"menu": "교촌윙", "price": "22000"},
         {"menu": "교촌스틱", "price": "22000"},
         {"menu": "교촌순살", "price": "22000"},
         {"menu": "레블반반콤보", "price": "23000"},
         {"menu": "반반오리지날（한마리）", "price": "20000"},
         {"menu": "반반콤보", "price": "23000"},
         {"menu": "반반윙", "price": "23000"},
         {"menu": "반반스틱", "price": "23000"},
         {"menu": "반반순살", "price": "23000"},
         {"menu": "레허반반순살", "price": "23000"},
         {"menu": "시그니처순살세트", "price": "33000"},
         {"menu": "리얼후라이드", "price": "20000"},
         {"menu": "살살후라이드", "price": "20000"},
         {"menu": "살살후라이드미니", "price": "7000"},
         {"menu": "파채소이살살", "price": "19000"},
         {"menu": "블랙시크릿콤보[S]", "price": "14000"},
         {"menu": "블랙시크릿순살[S]", "price": "14000"},
         {"menu": "허니순살[S]", "price": "13000"},
         {"menu": "레드순살[S]", "price": "13000"},
         {"menu": "교촌순살[S]", "price": "12000"},
         {"menu": "교촌콤보[S]", "price": "12000"},
         {"menu": "레드콤보[S]", "price": "13000"},
         {"menu": "레드윙[S]", "price": "13000"},
         {"menu": "교촌윙[S]", "price": "12000"},
         {"menu": "레드스틱[S]", "price": "13000"},
         {"menu": "교촌스틱[S]", "price": "12000"},
         {"menu": "옥수수볼", "price": "5000"},
         {"menu": "달걀듬뿍볶음밥", "price": "4000"},
         {"menu": "의성마늘볶음밥", "price": "4000"},
         {"menu": "포테이토앤칩스（점보팩）", "price": "6500"},
         {"menu": "국물맵떡", "price": "9000"},
         {"menu": "웨지감자", "price": "4000"},
         {"menu": "칩카사바", "price": "2000"},
         {"menu": "퐁듀치즈볼（6ea）", "price": "6000"},
         {"menu": "퐁듀치즈볼（3ea）", "price": "3500"},
         {"menu": "고르곤치즈볼（6ea）", "price": "6000"},
         {"menu": "고르곤치즈볼（3ea）", "price": "3500"},
         {"menu": "쫀드칸꽈배기（시나몬슈가）", "price": "4000"},
         {"menu": "닭갈비볶음밥", "price": "4000"},
         {"menu": "샐러드", "price": "5000"},
         {"menu": "레드시크릿볶음면컵（비조리）", "price": "2300"}
       ]

print(json.dumps(menu))