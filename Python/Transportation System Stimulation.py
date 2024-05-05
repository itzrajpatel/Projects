import time

class Bus:
    def __init__(self, bus_id, route):
        self.bus_id = bus_id
        self.route = route
        self.current_stop_index = 0
    
    def move_to_next_stop(self):
        if self.current_stop_index < len(self.route) - 1:
            self.current_stop_index += 1
            print(f"{self.bus_id} is moving to {self.route[self.current_stop_index]}")
        else:
            print(f"{self.bus_id} has reached the final destination.")
    
    def display_status(self):
        print(f"{self.bus_id} is currently at {self.route[self.current_stop_index]}")

class TransportationSystem:
    def __init__(self):
        self.buses = []
    
    def add_bus(self, bus_id, route):
        bus = Bus(bus_id, route)
        self.buses.append(bus)
    
    def start_simulation(self):
        print("Transportation system simulation started...\n")
        while True:
            all_buses_at_destination = all(bus.current_stop_index == len(bus.route) - 1 for bus in self.buses)
            if all_buses_at_destination:
                print("All buses have reached their destinations. Simulation ended.")
                break
            
            for bus in self.buses:
                bus.move_to_next_stop()
                time.sleep(2)  # Simulate time taken to travel between stops
                bus.display_status()
            time.sleep(1)  # Simulate delay between buses

# Example usage
if __name__ == "__main__":
    transportation_system = TransportationSystem()
    transportation_system.add_bus("Patel Travels", ["Ahmedabad", "Nadiad", "Bharuch", "Surat"])
    transportation_system.add_bus("Paras Travels", ["Ahmedabad", "Vadodara", "Bharuch", "Surat", "Valsad", "Daman"])
    transportation_system.start_simulation()
