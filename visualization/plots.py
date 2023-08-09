import matplotlib.pyplot as plt
from matplotlib.patches import Circle

from visualization.models import Board


def plot_particles_in_board(board: Board):
    fig, ax = plt.subplots(figsize=(8, 8))

    for particle in board.particles:
        ax.add_patch(
            Circle((particle.x, particle.y), radius=particle.radius, alpha=0.7)
        )

    ax.set_xlim(0, board.L)
    ax.set_ylim(0, board.L)
    ax.set_xlabel('X')
    ax.set_ylabel('Y')
    ax.set_title('Particle Distribution for time ' + str(board.time))
    ax.grid()

    plt.show()




