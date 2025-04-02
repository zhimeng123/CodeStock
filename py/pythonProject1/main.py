import tkinter as tk
import tkinter.messagebox  # 弹窗
import random
import copy


def InsertSort():  # 生成随机数组,并且保存插入排序的每步结果,保存到sortlist
    global sortlist
    num = [i for i in range(1, 31, 1)]  # 生成随机数据列表
    random.shuffle(num)
    num = num[:20]  # 取随机数组的前20来组成列表
    sortlist.append(copy.deepcopy(num))  # 存储每次排序结果

    for i in range(1, len(num)):  # 插入排序算法
        x = num[i]
        j = i - 1
        while j >= 0 and x < num[j]:
            num[j + 1] = num[j]
            j = j - 1
        num[j + 1] = x
        sortlist.append(copy.deepcopy(num))  # 存储每次排序结果
    sortlist.append(copy.deepcopy(num))


def Draw():
    global width, x0, y0, index, sortlist
    x0 = 100  # x 坐标初始化
    print(sortlist[index])

    # 单步排序按钮
    def step():  # 删除每一个矩形以及字符
        for i in range(20):
            canvas.delete(str(i))
            canvas.delete("string" + str(i))
        Draw()  # 重新绘制

    stepBtn = tk.Button(root, text="单步排序", width=8, height=1, command=step)
    stepBtn.place(x=260, y=420)

    # 重置数据按钮
    def reset():
        global index
        index = 0
        sortlist.clear()  # 清空结果列表
        InsertSort()  # 执行插入排序算法
        Draw()  # 绘制结果列表中的每一项

    resetBtn = tk.Button(root, text="重置数据", width=8, height=1, command=reset)
    resetBtn.place(x=430, y=420)

    canvas = tk.Canvas(root, bg='white', height=400, width=1000)  # 画布设置
    canvas.place(x=0, y=0)
    for i in range(20):
        # 绘制矩形条
        if index + 1 == i:  # 用红色标记待排序的数字
            canvas.create_rectangle(x0, y0 - sortlist[index][i] * 10, x0 + width, y0, width=3, fill='red', tags=str(i))
        else:
            canvas.create_rectangle(x0, y0 - sortlist[index][i] * 10, x0 + width, y0, width=3, tags=str(i))

        # 绘制文本
        canvas.create_text(x0 + width // 2, y0 - sortlist[index][i] * 10 - width // 2, text=str(sortlist[index][i]),
                           font="time 10 bold underline", tags="string" + str(i))
        x0 = x0 + width

    if index == 19:  # 数组长度限制为20
        tk.messagebox.showinfo('信息', '排序完成')  # 弹出窗口的显示信息
    index += 1


if __name__ == "__main__":
    root = tk.Tk()  # 生成root主窗口
    root.title("动态演示插入排序")  # 窗口标题
    root.geometry('780x500+250+250')  # 设置启动时窗口大小

    width = 30  # 矩形条宽度
    x0 = 100  # 矩形条x轴初始值
    y0 = 350  # 矩形条最高度
    index = 0  # 排序数字的下标
    sortlist = []  # 存储插入排序结果的列表

    InsertSort()  # 插入排序
    Draw()  # 绘制列表
    root.mainloop()
