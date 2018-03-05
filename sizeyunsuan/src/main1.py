#coding=utf-8
import random
from fractions import Fraction


def subject_creater1(total, scope):
    oscope = -scope
    num1 = 0
    num2 = 0
    num3 = ""
    counts = 1
    score = 0
    true = 0
    eval_combine = []
    eval_combine_str = ""
    while True:
        num1 = random.randint(oscope, scope)
        num2 = random.randint(oscope, scope)
        num3 = random.randint(oscope, scope)
        s1 = random.choice("+-*/")
        s2 = random.choice("+-*/")
        eval_combine.append(str(num1))
        eval_combine.append(str(s1))
        eval_combine.append(str(num2))
        eval_combine.append(str(s2))
        eval_combine.append(str(num3))

        eval_combine_str = "".join(eval_combine)

        sums = str(round(eval(eval_combine_str),2))

        print(eval_combine_str)
        ans = input()
        if ans == sums:
            print("你对了!")
            true = true + 1
        else:
            print("你错了,正确的答案是%s"%sums)
        eval_combine = []
        eval_combine_str = ""
        if counts is total:                      # 当 i = n 时，退出循环
            score = 100 * (true/total)
            print("您这次的总分是%d"%(score))
            break
        else:
            counts += 1

def subject_creater2(n):         # n 为题目个数
    x = 0
    y = 0
    z = ""
    i = 1
    sum = 0.0
    ans = 0.0
    score = 0
    true = 0
    while True:
        x = random.randint(0,10)        # 为变量 x 随机赋值
        y = random.randint(1,10)        # 为变量 y 随机赋值
        z = random.choice("+-*/")       # 为变量 z 随机赋符号
        if z is "+":                   # 根据 z 的符号判断相应的操作
            sum = x + y
        elif z is "-":
            sum = x - y
        elif z is "*":
            sum = x * y
        else:
            # sum = x / y
            sum = str(Fraction(x,y))
        print(x,z,y,"=?")
        if z is not "/":
            ans = float(input())
        else:
            ans = input()
        if ans == sum:
            print("你对了")
            true = true + 1
        else:
            print("你错了")
        if i is n:                      # 当 i = n 时，退出循环
            score = 100 * (true/n)
            print("您这次的总分是%d"%(score))
            break
        else:
            i += 1


number = 0
number = int(input("请输入题目的数量："))
scope = 0
scope = int(input("请输入数字的范围："))
print("开始答题，结果中是循环小数的话请保留两位小数")
subject_creater1(number,scope) # 执行函数Subject_Creater()
