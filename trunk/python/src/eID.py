import smartcard
from smartcard.util import toASCIIString, toHexString, HexListToBinString

SELECT = [0x00, 0xA4, 0x08, 0x0C, 0x06] # SELECT COMMAND -- Last '0x06' is the length of the subsequent data
MASTER = [0x3F, 0x00] # MASTER FILE
DATA = [0xDF, 0x01] # DATA FILE

EID_ID = [0x40, 0x31] # ID FIELD
EID_ADDRESS = [0x40, 0x33] # ADDRESS FIELD
EID_PICTURE = [0x40, 0x35] # PICTURE FIELD

READ = [0x00, 0xB0, 0x00, 0x00] # READ COMMAND (require additional length)

def parse(data):
    """ Simple TLV parser 
        @param data: file content
        @type data: binary data (list of hex value)
        
        @return: parsed data
        @rtype: dictionary (tag: value) 
    """
    result = {}
    offset = 0
    while offset < len(data) - 1:
        tag = data[offset]
        offset += 1
        length = data[offset]
        offset += 1
        value = toASCIIString(data[offset:offset+length])
        offset += length
        result[tag] = value
    return result

# List all available smartcard reader
r = smartcard.System.readers()
# connect to the first one -- I use ACR38U
connection = r[0].createConnection()
# establish connection
connection.connect()

for field in [EID_ID, EID_ADDRESS]: 
    # SELECT FILE TO READ
    data, sw1, sw2 = connection.transmit( SELECT + MASTER + DATA + field )
    # hex(sw1), hex(sw2) == 90 00 (success code)
    
    # READ TO FIND RIGHT SIZE (depends on content)
    data, sw1, sw2 = connection.transmit( READ + [0x00] )
    # hex(sw1), hex(sw2) == 6C length (wrong length, should be [sw2])
    
    # READ WITH RIGHT LENGTH
    data, sw1, sw2 = connection.transmit( READ + [sw2] )
    # hex(sw1), hex(sw2) == 90 00 (success code)
    
    result = parse(data)
    # iterate over the parsed dictionary and display the values
    for tag in result:
        # Attention: not all fields hold information eID source (see above) explains tag meaning
        print tag, result[tag]
        
# Read the Picture -- Little different method
data, sw1, sw2 = connection.transmit( SELECT + MASTER + DATA + EID_PICTURE)
f = open("picture.jpg", mode="wb")
#Do ....
for i in range(255):
    # Read the data with setps of 256 bytes
    data, sw1, sw2 = connection.transmit( [0x00, 0xB0, i, 0x00, 0x00] )
    # While ...
    if data == []:
        # find the end, decreasing loop
        for j in range(255,0,-1):
            data, sw1, sw2 = connection.transmit( [0x00, 0xB0, i, 0x00, j] )
            if data != []: 
                f.write(HexListToBinString(data))
                break
        break
    # write binary data to file
    f.write(HexListToBinString(data))    
f.close()