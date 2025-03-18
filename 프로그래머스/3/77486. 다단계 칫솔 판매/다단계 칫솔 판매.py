def solution(enroll, referral, seller, amount):
    answer = []
    parent = {}
    result = {}
    price = 100
    
    for i in range(len(referral)):
        if referral[i] != "-":
            parent[enroll[i]] = referral[i]
        
        else : parent[enroll[i]] = "-"
        
        result[enroll[i]] = 0

            
    for i in range(len(seller)):
        total_selling_price = amount[i] * price
        tax = total_selling_price // 10
        seller_income = total_selling_price - tax
        seller_name = seller[i]
        result[seller_name] += seller_income
        
        while parent[seller_name] != '-':
            total_selling_price = tax # 50
            tax = total_selling_price // 10 # 5
            seller_income = total_selling_price - tax

            seller_name = parent[seller_name]

            if seller_name == "mary":
                print(seller_income)

            result[seller_name] += seller_income
            if tax == 0:
                break
                
    for key in result.keys():
        answer.append(result[key])
    
    return answer