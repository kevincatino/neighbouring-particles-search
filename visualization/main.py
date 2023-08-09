from visualization.models import Board, Particle
from visualization.plots import plot_particles_in_board


# boards = parse_json()

board = Board(20, 6, 1, 0,
              [
                  Particle(1, 2, 3, 1, [2, 3]),
                  Particle(2, 15, 4, 3, []),
                  Particle(3, 7, 5, 2, [2])
              ]
              )

plot_particles_in_board(board)
