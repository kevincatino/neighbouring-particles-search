import matplotlib
import matplotlib.pyplot as plt
from matplotlib.patches import Circle

from visualization.models import Board, TimeMeasures


def plot_particles_in_board(board: Board):
    # https://stackoverflow.com/questions/61583476/pycharm-not-displaying-matplotlib-plot
    matplotlib.use('TkAgg')

    fig, ax = plt.subplots()

    circles_data = {}
    for particle in board.particles:
        circle = Circle((particle.x, particle.y), radius=particle.radius, alpha=0.7, color='b')
        circles_data[particle.id] = {'particle': particle, 'circle': circle}
        ax.add_patch(circle)

    def motion_hover(event):
        nonlocal circles_data
        if event.inaxes == ax:
            hovered_circle = None
            hovered_particle = None
            for particle_id, data in circles_data.items():
                c = data['circle']
                contains, _ = c.contains(event)
                if contains:
                    hovered_circle = c
                    hovered_particle = data['particle']
                    break
            if hovered_circle and hovered_particle:
                hovered_circle.set_color('red')
                for neighbour_id in hovered_particle.neighbours:
                    if neighbour_id in circles_data:
                        neighbour_circle = circles_data[neighbour_id]['circle']
                        neighbour_circle.set_color('green')
            else:
                for particle_id, data in circles_data.items():
                    c = data['circle']
                    original_color = 'b'  # Color original
                    c.set_color(original_color)
        fig.canvas.draw()

    fig.canvas.mpl_connect('motion_notify_event', motion_hover)

    subgrid_size = board.cell_length
    x_ticks = []
    y_ticks = []

    for i in range(board.M + 1):
        x_tick_value = subgrid_size * i
        y_tick_value = subgrid_size * i
        x_ticks.append(x_tick_value)
        y_ticks.append(y_tick_value)

    ax.set_xticks(x_ticks)
    ax.set_yticks(y_ticks)
    ax.grid()

    ax.set_xlim(0, board.L)
    ax.set_ylim(0, board.L)

    ax.set_xlabel('X')
    ax.set_ylabel('Y')
    ax.set_title('Particle Distribution for time ' + str(board.time))

    plt.show()


def plot_times(time_measures: TimeMeasures):
    fig, ax = plt.subplots()

    ax.plot(time_measures.particles_count, time_measures.bf_times, label='Brute force')
    ax.plot(time_measures.particles_count, time_measures.cim_times, label='CIM')

    ax.set_xlabel('Particles')
    ax.set_ylabel('Time [ms]')
    ax.set_title('Brute force vs CIM method')
    ax.grid(True)
    plt.legend()
    plt.show()
