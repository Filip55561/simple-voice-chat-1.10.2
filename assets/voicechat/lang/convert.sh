#!/usr/bin/bash

LANG_FOLDER="./"

for file in "$LANG_FOLDER"/*.lang; do
    base=$(basename "$file" .lang)
    IFS='_' read -r lang country <<< "$base"
    new_name="${lang,,}_${country^^}.lang"
    if [[ "$file" != "$LANG_FOLDER/$new_name" ]]; then
        echo "Renaming $file -> $new_name"
        mv "$file" "$LANG_FOLDER/$new_name"
    fi
done
