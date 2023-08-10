import json
import models


def parse_json():
    # Opening JSON file
    f = open('../sequence4.json')

    # returns JSON object as
    # a dictionary
    json_input = json.load(f)

    L = json_input['l']
    M = json_input['m']
    rc = json_input['rc']
    data = json_input['data']

    # Closing file
    f.close()

    board_list = []

    for time in data:
        particles = []
        for part in time['particles']:
            obj_particle = models.Particle(part['id'], part['coordinates']['x'], part['coordinates']['y'], part['radius'])
            for n in part['neigbours']:
                neighbour = n['id']
                obj_particle.neighbours.append(neighbour)
            particles.append(obj_particle)
        board_list.append(models.Board(L, M, rc, time['time'], particles))

    for b in board_list:
        #print("L:" + b.L + " Time:" + b.time + " rc:" + b.rc)
        for part in b.particles:
            print(part)
    return board_list


def parse_time_json():
    # Opening JSON file
    f = open('stats.json')

    # returns JSON object as
    # a dictionary
    json_input = json.load(f)

    time_measures = models.TimeMeasures()

    for t in json_input:
        time_measures.particles_count.append(t['Particles'])
        time_measures.bf_times.append(t['bruteForce'])
        time_measures.cim_times.append(t['CIM'])

    return time_measures
