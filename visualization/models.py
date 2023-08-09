
class Particle:
    def __init__(self, particle_id: int, x: float, y: float, radius: float):
        self.id = particle_id
        self.x = x
        self.y = y
        self.radius = radius
        self.neighbours = []

    def __str__(self):
        return f'({self.x}, {self.y})'


class Board:
    def __init__(self, L: int, M: int, rc: int, time: int, particles: list[Particle]):
        self.L = L
        self.M = M
        self.rc = rc
        self.time = time
        self.particles = particles

    @property
    def cell_length(self):
        return self.L / self.M



