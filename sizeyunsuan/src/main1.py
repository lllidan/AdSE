# coding=utf-8
import random
import time
from fractions import Fraction


def threeParameter(total, scope,jingdu,negative):
    """

    :param total: 生成计算题的数量
    :param scope:题中每个因子的范围
    :param jingdu:小数的精度
    :param negative:是否包含有负数
    function: 根据传入的参数，定制打印出的题目数
    """
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
    ans = 0
    anses = ""
    while True:
        # 根据输入的参数，随机生成对应的参加操作的操作数
        if negative == 'y':
            # 有负数
            num1 = random.randint(oscope, scope)
            num2 = random.randint(oscope, scope)
            num3 = random.randint(oscope, scope)
        else:
            # 不存在负数
            num1 = random.randint(0, scope)
            num2 = random.randint(0, scope)
            num3 = random.randint(0, scope)
        # 生成对应的操作运算符
        s1 = random.choice("+-*/")
        s2 = random.choice("+-*/")
        # 将操作符、运算数组合成完整的表达式
        eval_combine.append(str(num1))
        eval_combine.append(str(s1))
        eval_combine.append(str(num2))
        eval_combine.append(str(s2))
        eval_combine.append(str(num3))
        eval_combine_str = "".join(eval_combine)
        # 判断生成的表达式是否已经存在， 如果已经存在，则重新生成
        if eval_combine_str not in questions:
            # 如果不存在，加入问题列表存储
            questions.add(eval_combine_str)
        else:
            continue
        # 计算表达式的值
        sums = str(round(eval(eval_combine_str), jingdu))
        # 保存答案
        anses = anses + str(counts) + "、" + str(sums) + "  "
        # 将表达式打印到屏幕上
        print(eval_combine_str + "=")
        # 接收用户输入的答案
        ans = input()
        if ans == sums:
            print("你对了!")
            # 正确数量加一
            true = true + 1
        else:
            # 回答错误的时候提醒正确答案
            print("你错了,正确的答案是%s"%sums)
        # 清空存储变量
        eval_combine = []
        eval_combine_str = ""
        if counts is total:
            # 计算得分
            calscore(true, number)
            writeYN = input("是否要将本次的练习题写入到文件中？(y/n)")
            if writeYN == 'y':
                writetofile(questions, anses)
            break
        else:
            counts += 1


def twoParameter(number):
    """
    最简单的计算函数，接收一个题目数量的参数number，然后打印简单的题
    :param
        number: 题目数量
    :var
        num1:第一个操作数
        num2:第二个操作数
        counts:计算的次数
        score:分数
        true:做对的题的数目
        eval_combine:组合的表达式
        eval_combine_str:存储组合形成的表达式的字符串
        questions:存储全部的题目
        sums:存储标准答案
        ans:存储用户输入的答案
        anses：存储全部的标准答案

    :return:
    """
    num1 = 0
    num2 = 0
    counts = 1
    score = 0
    true = 0
    eval_combine = []
    eval_combine_str = ""
    questions = set()
    ans = 0
    anses = ""

    print("除法结果保留两位小数，整数保留小数点后第一个零")
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

        anses = anses + str(counts) + "、" + str(sums) + "  "

        if eval_combine_str not in questions:
            questions.add(eval_combine_str)
        else:
            eval_combine = []
            eval_combine_str = ""
            continue
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
            writeYN = input("是否要将本次的练习题写入到文件中？(y/n)")
            if writeYN == 'y':
                writetofile(questions,anses)
            break
        else:
            counts += 1


def testpaper(number, scope, jingdu, negative):
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
    ans = 0
    anses = ""
    while True:
        print("第%d道" % counts)
        if negative == 'y':
            num1 = random.randint(oscope, scope)
            num2 = random.randint(oscope, scope)
            num3 = random.randint(oscope, scope)
        else:
            num1 = random.randint(1, scope)
            num2 = random.randint(1, scope)
            num3 = random.randint(1, scope)
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

        try:
            sums = str(round(eval(eval_combine_str), jingdu))
        except ZeroDivisionError as e:
            print("except:", e)
            eval_combine = []
            eval_combine_str = ""
            continue


        anses = anses + str(counts) + "、" + str(sums) + "  "
        eval_combine = []
        eval_combine_str = ""

        if counts is number:
            break
        else:
            counts = counts + 1


    name = "testpapaer.txt"
    with open(name, 'w') as f:
        f.write(time.strftime("%Y-%m-%d %H:%M:%S\n", time.localtime(time.time())))
        f.write("欢迎参加考试，考试题的规则如下：\n")
        f.write("考试题目共有%d道\n" % number)
        f.write("考试题目中出现的最大数字为%d\n" % scope)
        f.write("试题答案应该保留的精度为%d位\n" % jingdu)
        if negative == 1:
            f.write("题目中包含负数\n")
        else:
            f.write("题目中不不包含负数\n")

        f.write("----------考试题目如下---------")
        for question in questions:
            f.write(question + "=\n")
        f.write("----------所有题的答案如下：------------\n")
        f.write(anses)
    print("题目文件生成成功，名字为%s" % name)


def calscore(trues,numbers):
    score = 100 * (trues / numbers)
    print("您这次的总分是%d" % (score))


def writetofile(questions, anses):
    name = time.strftime('%Y-%m-%d-%H_%M_%S', time.localtime(time.time()))
    name = name+".txt"
    with open(name, 'w') as f:
        f.write(time.strftime("%Y-%m-%d %H:%M:%S\n", time.localtime(time.time())))
        f.write("题目如下：\n")
        for question in questions:
            f.write(question+"=\n")
        f.write("所有题的答案如下：\n")
        f.write(anses)
    print("题目文件生成成功，名字为%s"%name)






print("欢迎使用数学计算题学习系统：")
difficult = 0
number = 0
mode = 0
mode = int(input("请输入需要选择的模式：1、练习模式 2、考试模式"))
if mode == 1:
    difficult = int(input("请输入选择的类型：1、简单(两个操作数的小数字运算)2、多操作数的大数字计算"))
    number = int(input("请输入题目的数量："))
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
else :
    number = int(input("请输入题目的数量："))
    scope = 0
    scope = int(input("请输入数字的范围："))
    jingdu = 0
    jingdu = int(input("请输入保存的精度："))
    negative = ''
    negative = input("操作数中是否包含有负数(y/n)")
    print("开始答题，结果中是循环小数的话请保留所指定的精度")
    testpaper(number, scope, jingdu, negative)