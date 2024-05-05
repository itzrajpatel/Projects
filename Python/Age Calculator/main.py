from kivy.app import App
from kivy.uix.gridlayout import GridLayout
from kivy.uix.label import Label
from kivy.uix.image import Image
from kivy.uix.button import Button
from kivy.uix.textinput import TextInput
from kivy.core.window import Window
from kivy.graphics import Color, Rectangle
from kivy.utils import get_color_from_hex
from kivy.uix.popup import Popup
from datetime import datetime
import mysql.connector

class AgeCalculator(App):
    def build(self):
        self.window = GridLayout()
        self.window.cols = 1
        self.window.size_hint = (0.6, 0.7)
        self.window.pos_hint = {"center_x": 0.5, "center_y": 0.5}
        
        # Background Color
        with self.window.canvas.before:
            Color(*get_color_from_hex('#6DA0F5'))  # R, G, B, A (A is the alpha channel for transparency)
            self.rect = Rectangle(size=(Window.width, Window.height), pos=self.window.pos)
        
        self.window.add_widget(Image(source="images.png"))

        self.nameRequest = Label(
            text="[color=#ffffff][b]Enter your name...[/b][/color]",
            font_size=50,
            markup=True
        )
        self.window.add_widget(self.nameRequest)

        self.name_input = TextInput(
            multiline=False,
            padding_y=(30, 30),
            size_hint=(1, 0.80),
            font_size=50
        )
        self.window.add_widget(self.name_input)

        self.ageRequest = Label(
            text="[color=#ffffff][b]Enter your year of birth (YYYY)...[/b][/color]",
            font_size=50,
            markup=True
        )
        self.window.add_widget(self.ageRequest)

        # Prepopulate date of birth with the current year
        current_year = datetime.today().year
        self.date = TextInput(
            text=str(current_year),
            multiline=False,
            padding_y=(30, 30),
            size_hint=(1, 0.7),
            font_size=50,
            input_filter="int",
            input_type="number"
        )
        self.window.add_widget(self.date)

        self.button = Button(
            text="[b]Calculate Age[/b]",
            size_hint=(0.5, 0.5),
            bold=True,
            font_size=30,
            markup=True,
            pos_hint={"center_x": 0.5, "top": 0.9}
        )
        self.button.bind(on_press=self.getAge)
        self.window.add_widget(self.button)

        return self.window

    def show_popup(self, content):
        popup = Popup(title='Error!',
                      content=Label(text=content, font_size=30, markup=True),
                      size_hint=(None, None), size=(500, 200))
        popup.open()

    def getAge(self, event):
        # Database connection
        try:
            mydb = mysql.connector.connect(
                host="localhost",
                user="root",
                password="itzrajpatel_004_",
                database="mydatabase"
            )
            mycursor = mydb.cursor()

            today = datetime.today().year
            dob = int(self.date.text)  # Convert to integer
            if dob > today:
                self.show_popup("[color=#ff0000]Please enter a valid year of birth[/color]")
                return
            age = today - dob
            self.show_popup("[color=#ffffff]You are[/color] [color=#00ff00][b]" + str(age) + "[/b][/color] [color=#ffffff]years old[/color]")

            # Inserting into the database
            sql = "INSERT INTO age_logs (name, date_of_birth, age) VALUES (%s, %s, %s)"
            val = (self.name_input.text, str(dob), age)  # Date of birth should be a string
            mycursor.execute(sql, val)
            mydb.commit()
            print(mycursor.rowcount, "record inserted.")

        except mysql.connector.Error as err:
            print("An error occurred:", err)

        finally:
            if 'mydb' in locals() and mydb.is_connected():
                mycursor.close()
                mydb.close()

if __name__ == "__main__":
    AgeCalculator().run()