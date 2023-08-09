from visualization.models import Board, Particle
from visualization.plots import plot_particles_in_board, plot_times
from visualization.utils import parse_time_json

# boards = parse_json()

# board = Board(20, 6, 1, 0,
#               [
#                   Particle(1, 2, 3, 1, [2, 3]),
#                   Particle(2, 15, 4, 3, []),
#                   Particle(3, 7, 5, 2, [2])
#               ]
#               )
#
# plot_particles_in_board(board)

time_measures = parse_time_json()
plot_times(time_measures)
