import json
import models


def parse_json():
    # Opening JSON file
    f = open('data.json')

    # returns JSON object as
    # a dictionary
    json_input = json.load(f)

    L = json_input['L']
    M = json_input['M']
    rc = json_input['rc']
    data = json_input['data']

    # Closing file
    f.close()

    board_list = []

    for time in data:
        particles = []
        for part in time['particles']:
            obj_particle = models.Particle(part['id'], part['coordinates'][0], part['coordinates'][1], part['radius'])
            for n in part['neighbours']:
                neighbour = models.Particle(part['id'], part['coordinates'][0], part['coordinates'][1], part['radius'])
                obj_particle.neighbours.append(neighbour)
            particles.append(obj_particle)
        board_list.append(models.Board(L, M, rc, time['time'], particles))

    return board_list
