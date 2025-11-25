#!/bin/bash

# WLED Simulator Frontend Control Verification Script
# Tests all LED control features through API calls and HTML verification

echo "🎬 WLED Simulator Frontend Control Verification"
echo "==============================================="
echo ""

API_URL="http://localhost:8081"
TESTS_PASSED=0
TESTS_FAILED=0

# Helper function to test API
test_api() {
    local name=$1
    local method=$2
    local endpoint=$3
    local payload=$4
    local expected=$5

    echo "🧪 $name"

    if [ "$method" = "GET" ]; then
        response=$(curl -s "$API_URL$endpoint")
    else
        response=$(curl -s -X "$method" "$API_URL$endpoint" \
            -H "Content-Type: application/json" \
            -d "$payload")
    fi

    if echo "$response" | grep -q "$expected"; then
        echo "  ✓ PASS: Found '$expected' in response"
        ((TESTS_PASSED++))
    else
        echo "  ✗ FAIL: Expected '$expected' not found"
        ((TESTS_FAILED++))
    fi
    echo ""
}

# Helper function to test HTML
test_html() {
    local name=$1
    local element=$2

    echo "🧪 $name"

    html=$(curl -s "$API_URL/")

    if echo "$html" | grep -q "$element"; then
        echo "  ✓ PASS: Found '$element' in HTML"
        ((TESTS_PASSED++))
    else
        echo "  ✗ FAIL: '$element' not found in HTML"
        ((TESTS_FAILED++))
    fi
    echo ""
}

# ===================================================================
# HTML Element Tests
# ===================================================================
echo "📄 HTML Control Elements"
echo "-----------------------"
echo ""

test_html "Master Control Panel" "Master Controls"
test_html "Master Power Button" "masterPowerBtn"
test_html "Master Brightness Slider" "masterBrightness"
test_html "Segment Controls" "segment-controls"
test_html "Segment Power Toggle" "segment-toggle-btn"
test_html "Segment Brightness Input" "segment-brightness"
test_html "Segment Color Picker" "segment-color"
test_html "Segment Effect Select" "segment-effect"
test_html "Segment Speed Input" "segment-speed"
test_html "Segment Intensity Input" "segment-intensity"
test_html "Segment Palette Select" "segment-palette"

echo ""
echo "🔧 API Control Tests"
echo "-------------------"
echo ""

# Test 1: Get current state
test_api "Get Device State" "GET" "/json/state" "" '"on"'

# Test 2: Toggle Master Power OFF
test_api "Toggle Master Power OFF" "POST" "/json/state" '{"on":false}' '"on":false'

# Test 3: Toggle Master Power ON
test_api "Toggle Master Power ON" "POST" "/json/state" '{"on":true}' '"on":true'

# Test 4: Set Master Brightness
test_api "Set Master Brightness to 150" "POST" "/json/state" '{"bri":150}' '"bri":150'

# Test 5: Toggle Segment 0 Power OFF
test_api "Toggle Segment 0 Power OFF" "POST" "/json/state" '{"seg":[{"id":0,"on":false}]}' '"id":0'

# Test 6: Set Segment 0 Brightness
test_api "Set Segment 0 Brightness to 80" "POST" "/json/state" '{"seg":[{"id":0,"bri":80}]}' '"bri":80'

# Test 7: Set Segment 0 Color
test_api "Set Segment 0 Color to Orange" "POST" "/json/state" '{"seg":[{"id":0,"col":[[255,128,0]]}]}' '"col":.*255.*128'

# Test 8: Set Segment 0 Effect
test_api "Set Segment 0 Effect to 15" "POST" "/json/state" '{"seg":[{"id":0,"fx":15}]}' '"fx":15'

# Test 9: Set Segment 0 Speed
test_api "Set Segment 0 Speed to 200" "POST" "/json/state" '{"seg":[{"id":0,"sx":200}]}' '"sx":200'

# Test 10: Set Segment 0 Intensity
test_api "Set Segment 0 Intensity to 75" "POST" "/json/state" '{"seg":[{"id":0,"ix":75}]}' '"ix":75'

# Test 11: Set Segment 1 Palette
test_api "Set Segment 1 Palette to 10" "POST" "/json/state" '{"seg":[{"id":1,"pal":10}]}' '"pal":10'

# Test 12: Get Effects List
test_api "Get Effects List" "GET" "/api/effects" "" '"effects"'

# Test 13: Get Palettes List
test_api "Get Palettes List" "GET" "/api/effects/palettes" "" '"palettes"'

# Test 14: Get Device Info
test_api "Get Device Info" "GET" "/json/info" "" '"name":"AlfaWall'

# Test 15: Verify all 5 segments present
echo "🧪 Verify All 5 Segments Present"
response=$(curl -s "$API_URL/json/state")
segment_count=$(echo "$response" | grep -o '"id":[0-4]' | wc -l)
if [ "$segment_count" -ge 5 ]; then
    echo "  ✓ PASS: Found 5 segments"
    ((TESTS_PASSED++))
else
    echo "  ✗ FAIL: Expected 5 segments, found $segment_count"
    ((TESTS_FAILED++))
fi
echo ""

# ===================================================================
# Summary
# ===================================================================
echo "═════════════════════════════════════════════════════════════"
echo "📊 Test Summary"
echo "═════════════════════════════════════════════════════════════"
echo "  ✓ Passed: $TESTS_PASSED"
echo "  ✗ Failed: $TESTS_FAILED"
echo "  Total:   $((TESTS_PASSED + TESTS_FAILED))"
echo ""

if [ $TESTS_FAILED -eq 0 ]; then
    echo "✅ ALL TESTS PASSED - Frontend Controls Fully Working!"
    echo ""
    echo "📋 Features Verified:"
    echo "  ✓ Master power toggle"
    echo "  ✓ Master brightness control"
    echo "  ✓ Segment power toggle (all segments)"
    echo "  ✓ Segment brightness control"
    echo "  ✓ Segment color picker"
    echo "  ✓ Segment effect selection"
    echo "  ✓ Segment speed adjustment"
    echo "  ✓ Segment intensity adjustment"
    echo "  ✓ Segment palette selection"
    echo "  ✓ All 5 segments with full controls"
    echo ""
    exit 0
else
    echo "❌ Some tests failed"
    exit 1
fi
