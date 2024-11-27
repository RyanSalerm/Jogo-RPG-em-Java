from PIL import Image

# Abrir o GIF
gif = Image.open("Computador.png")

# Criar uma nova lista de frames invertidos
frames_invertidos = []

# Iterar sobre todos os frames do GIF
for frame in range(gif.n_frames):
    gif.seek(frame)
    # Inverter horizontalmente o frame
    frame_invertido = gif.transpose(Image.FLIP_LEFT_RIGHT)
    frames_invertidos.append(frame_invertido)

# Salvar o novo GIF com a animação invertida
frames_invertidos[0].save("Computadoro.png", save_all=True, append_images=frames_invertidos[1:], loop=0)
