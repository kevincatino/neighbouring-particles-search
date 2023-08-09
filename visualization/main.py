from visualization.plots import plot_particles_in_board
from visualization.utils import parse_json

boards = parse_json()

for board in boards:
    plot_particles_in_board(board)