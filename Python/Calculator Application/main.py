from kivy.app import App
from kivy.uix.button import Button
from kivy.uix.boxlayout import BoxLayout
from kivy.uix.textinput import TextInput
import mysql.connector

class MainApp(App):
    def build(self):
        # Set the icon for the application
        self.icon = "calculator.png"
        
        self.title = "Calculator"
        # List of operators
        self.operators = ["+", "-", "*", "/"]
        # Flag to track if the last entry was an operator
        self.last_was_operator = None
        # Variable to hold the last button pressed
        self.last_button = None

        # Main layout
        main_layout = BoxLayout(orientation = "vertical")
        # Text input field
        self.solution = TextInput(background_color = "black", foreground_color = "white", multiline = False, halign = "right", font_size = 70, readonly = True)
        main_layout.add_widget(self.solution)

        # Buttons layout
        buttons = [
            ["7", "8", "9", "/"],
            ["4", "5", "6", "*"],
            ["1", "2", "3", "+"],
            [".", "0", "C", "-"],
        ]

        # Loop through the button list to create and add buttons
        for row in buttons:
            h_layout = BoxLayout()
            for label in row:
                button = Button(
                    text = label, font_size = 40, background_color = "grey",
                    pos_hint = { 'center_x': 0.5, 'center_y': 0.5 }
                )
                button.bind(on_press = self.on_button_press)
                h_layout.add_widget(button)
            main_layout.add_widget(h_layout)

        # Add the equal button
        equal_button = Button (
            text = "=", font_size = 40, background_color = (0.678, 0.847, 0.902, 1),
            pos_hint = { 'center_x': 0.5, 'center_y': 0.5 }
        )
        equal_button.bind(on_press = self.on_solution)
        main_layout.add_widget(equal_button)

        # Return the main layout
        return main_layout
    
    # Function to handle button presses
    def on_button_press(self, instance):
        current = self.solution.text
        button_text = instance.text

        if  button_text == "C":
            self.solution.text = ""
        else:
            if current and (self.last_was_operator and button_text in self.operators):
                return
            elif current == "" and button_text in self.operators:
                return
            else:
                new_text = current + button_text
                self.solution.text = new_text
        self.last_button = button_text
        self.last_was_operator = self.last_button in self.operators

    # Function to evaluate the solution
    def on_solution(self, instance):
        text = self.solution.text
        if text:
            try:
                # Connect to MySQL
                connection = mysql.connector.connect(
                    host="localhost",
                    user="root",
                    password="itzrajpatel_004_",
                    database="mydatabase"
                )
                
                cursor = connection.cursor()

                # Calculate the result
                result = str(eval(self.solution.text))

                # Store the calculation into the database
                cursor.execute("INSERT INTO calculations (expression, result) VALUES (%s, %s)", (text, result))
                connection.commit()

                # Update the text field with the result
                self.solution.text = result

            except Exception as e:
                print("Error:", e)

            finally:
                # Close the connection
                if connection.is_connected():
                    cursor.close()
                    connection.close()

# Run the application
if __name__ == "__main__":
    app = MainApp()
    app.run()