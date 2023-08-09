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
            obj_particle = models.Particle(part['id'], part['coordinates']['x'], part['coordinates']['y'], part['radius'])
            for n in part['neighbours']:
                neighbour = models.Particle(part['id'], part['coordinates']['x'], part['coordinates']['y'], part['radius'])
                obj_particle.neighbours.append(neighbour)
            particles.append(obj_particle)
        board_list.append(models.Board(L, M, rc, time['time'], particles))

    return board_list


def parse_time_json():
    # Opening JSON file
    f = open('time.json')

    # returns JSON object as
    # a dictionary
    json_input = json.load(f)

    time_measures = models.TimeMeasures()

    for t in json_input:
        time_measures.particles_count.append(t['Particles'])
        time_measures.bf_times.append(t['bruteForce'])
        time_measures.cim_times.append(t['CIM'])

    return time_measures
