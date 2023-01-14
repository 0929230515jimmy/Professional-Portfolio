import turtle


def irma_setup():
    
    import tkinter
    turtle.setup(965, 600)  

    wn = turtle.Screen()
    wn.title("Hurricane Irma")

    canvas = wn.getcanvas()
    turtle.setworldcoordinates(-90, 0, -17.66, 45)  

    map_bg_img = tkinter.PhotoImage(file="images/atlantic-basin.png")

    canvas.create_image(-1175, -580, anchor=tkinter.NW, image=map_bg_img)

    t = turtle.Turtle()
    wn.register_shape("images/hurricane.gif")
    t.shape("images/hurricane.gif")

    return (t, wn, map_bg_img)


def irma():
    (t, wn, map_bg_img) = irma_setup()
    text = open("data/irma.csv")
    lines = text.readlines()
    category = 0

    t.penup()
    
    for line in lines[1:]:
        line = line.strip().split(",")
        lat = float(line[2])
        lon = float(line[3])
        wind = float(line[4])

        if wind < 74:
            t.color("white")
            t.width(1)
        elif 74 <= wind <=95:
            t.color("blue")
            t.width(4)
            category = 1
            t.write("1", font=("Ariel", 15))
        elif 96<= wind <= 110:
            t.color("green")
            t.width(6)
            category = 2
            t.write("2", font=("Ariel",15))
        elif 111 <= wind <= 129:
            t.color("yellow")
            t.width(8)
            category = 3
            t.write("3", font=("Ariel",15))            
        elif 130 <= wind <= 156:
            t.color("orange")
            t.width(10)
            category = 4
            t.write("4", font= ("Ariel",15))
        else:
            t.color("red")
            t.width(12)
            category = 5
            t.write("5", font= ("Ariel",15))

        t.goto(lon,lat)
        t.pendown()
        t.color("black")
        t.write(category)
    wn.mainloop()
            

       
            
    # your code to animate Irma here

if __name__ == "__main__":
    irma()
