# coding=utf-8
import random
import time
from fractions import Fraction


def threeParameter(total, scope,jingdu,negative):
    oscope = -scope
    num1 = 0
    num2 = 0
    num3 = 0
    counts = 1
    score = 0
    true = 0
    eval_combine = []
    eval_combine_str = ""
    questions = set()
    while True:
        if negative == 'y':
            num1 = random.randint(oscope, scope)
            num2 = random.randint(oscope, scope)
            num3 = random.randint(oscope, scope)
        else:
            num1 = random.randint(0, scope)
            num2 = random.randint(0, scope)
            num3 = random.randint(0, scope)
        s1 = random.choice("+-*/")
        s2 = random.choice("+-*/")
        eval_combine.append(str(num1))
        eval_combine.append(str(s1))
        eval_combine.append(str(num2))
        eval_combine.append(str(s2))
        eval_combine.append(str(num3))

        eval_combine_str = "".join(eval_combine)
        if eval_combine_str not in questions:
            questions.add(eval_combine_str)
        else:
            continue
        sums = str(round(eval(eval_combine_str),jingdu))

        print(eval_combine_str)
        ans = input()
        if ans == sums:
            print("你对了!")
            true = true + 1
        else:
            print("你错了,正确的答案是%s"%sums)
        eval_combine = []
        eval_combine_str = ""
        if counts is total:
            calscore(true, number)
            writeYN = input("是否要将本次的练习题写入到文件中？(y/n)")
            if writeYN == 'y':
                writetofile(questions)
            break
        else:
            counts += 1


def twoParameter(number):
    """
    最简单的计算函数，接收一个题目数量的参数number，然后打印简单的题
    :param number: 题目数量
    :return:
    """
    num1 = 0
    num2 = 0
    counts = 1
    score = 0
    true = 0
    eval_combine = []
    eval_combine_str = ""
    while True:
        num1 = random.randint(-10, 10)
        s1 = random.choice("+-*/")
        if s1 == "/":
            while num2 == 0:
                num2 = random.randint(-10, 10)
        # 保证除数不等于0
        eval_combine.append(str(num1))
        eval_combine.append(str(s1))
        eval_combine.append(str(num2))

        eval_combine_str = "".join(eval_combine)

        sums = str(round(eval(eval_combine_str), 2))

        print(eval_combine_str)
        ans = input()
        if ans == sums:
            print("你对了")
            true = true + 1
        else:
            print("你错了,正确的答案是%s"%sums)

        eval_combine = []
        eval_combine_str = ""
        if counts is number:
            calscore(true,number)
            break
        else:
            counts += 1

def calscore(true,number):
    score = 100 * (true / number)
    print("您这次的总分是%d" % (score))


def writetofile(strs):
    name = time.strftime('%Y-%m-%d-%H_%M_%S', time.localtime(time.time()))
    name = name+".txt"
    with open(name, 'w') as f:
        for str in strs:
            f.write(str+"=\n")


number = 0
number = int(input("请输入题目的数量："))
difficult = 0
difficult = int(input("请输入选择的类型：1、简单(两个操作数的小数字运算)2、多操作数的大数字计算"))
if difficult == 1:
    twoParameter(number)
else:
    scope = 0
    scope = int(input("请输入数字的范围："))
    jingdu = 0
    jingdu = int(input("请输入保存的精度："))
    negative = ''
    negative = input("操作数中是否包含有负数(y/n)")
    print("开始答题，结果中是循环小数的话请保留所指定的精度")
    threeParameter(number,scope,jingdu,negative) # 执行函数Subject_Creater()
