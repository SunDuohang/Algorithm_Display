import matplotlib.pyplot as plt
import matplotlib.patches as patches
import numpy as np
from matplotlib.patches import Polygon
import math

# Create figure and axis
fig, ax = plt.subplots(figsize=(8, 8))
ax.set_xlim(0, 3)
ax.set_ylim(0, 3)
ax.set_aspect('equal')

# Draw 9-grid (3x3)
for i in range(4):
    # Vertical lines
    ax.axvline(x=i, color='black', linewidth=2)
    # Horizontal lines
    ax.axhline(y=i, color='black', linewidth=2)

# Function to draw a five-pointed star
def draw_star(ax, center_x, center_y, radius, color='red'):
    # Calculate the points of a five-pointed star
    angles = np.linspace(0, 2*np.pi, 11, endpoint=False)
    star_points = []
    
    for i, angle in enumerate(angles):
        if i % 2 == 0:
            # Outer points
            x = center_x + radius * np.cos(angle)
            y = center_y + radius * np.sin(angle)
        else:
            # Inner points
            x = center_x + radius * 0.4 * np.cos(angle)
            y = center_y + radius * 0.4 * np.sin(angle)
        star_points.append([x, y])
    
    # Create and add the star polygon
    star = Polygon(star_points, facecolor=color, edgecolor='red', linewidth=1)
    ax.add_patch(star)

# Draw the red five-pointed star in the center (grid position 1,1)
center_x, center_y = 1.5, 1.5
star_radius = 0.3
draw_star(ax, center_x, center_y, star_radius, 'red')

# Define the 8 adjacent grid centers
adjacent_centers = [
    (0.5, 0.5),  # bottom-left
    (1.5, 0.5),  # bottom-center
    (2.5, 0.5),  # bottom-right
    (0.5, 1.5),  # center-left
    (2.5, 1.5),  # center-right
    (0.5, 2.5),  # top-left
    (1.5, 2.5),  # top-center
    (2.5, 2.5)   # top-right
]

# Draw red arrows from the star center to each adjacent grid center
arrow_length = 0.8
for adj_x, adj_y in adjacent_centers:
    # Calculate direction vector
    dx = adj_x - center_x
    dy = adj_y - center_y
    
    # Normalize and scale to arrow length
    length = math.sqrt(dx**2 + dy**2)
    if length > 0:
        dx = dx / length * arrow_length
        dy = dy / length * arrow_length
    
    # Draw arrow
    ax.annotate('', 
                xy=(center_x + dx, center_y + dy),
                xytext=(center_x, center_y),
                arrowprops=dict(arrowstyle='->', color='red', lw=2, mutation_scale=20))

# Remove axes and ticks
ax.set_xticks([])
ax.set_yticks([])
ax.spines['top'].set_visible(False)
ax.spines['right'].set_visible(False)
ax.spines['bottom'].set_visible(False)
ax.spines['left'].set_visible(False)

# Set title
ax.set_title('9-Grid with Red Star and Arrows', fontsize=16, pad=20)

# Adjust layout to minimize blank edges
plt.tight_layout()

# Save the image with high DPI and SVG format
plt.savefig('grid_star_arrows.svg', format='svg', dpi=300, bbox_inches='tight', pad_inches=0.1)
plt.savefig('grid_star_arrows.png', format='png', dpi=300, bbox_inches='tight', pad_inches=0.1)

print("Images saved as 'grid_star_arrows.svg' and 'grid_star_arrows.png' with DPI=300")
plt.show()